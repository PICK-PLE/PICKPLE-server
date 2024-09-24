package com.pickple.server.global.auth.jwt.filter;

import static com.pickple.server.global.auth.jwt.provider.JwtValidationType.EXPIRED_JWT_TOKEN;
import static com.pickple.server.global.auth.jwt.provider.JwtValidationType.VALID_JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickple.server.global.auth.jwt.provider.JwtTokenProvider;
import com.pickple.server.global.auth.security.UserAuthentication;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = getJwtFromRequest(request);

            if (token != null && jwtTokenProvider.validateToken(token) == VALID_JWT) {
                Long memberId = jwtTokenProvider.getUserFromJwt(token);

                // 토큰에서 roles 정보를 가져옴
                List<String> roles = jwtTokenProvider.getRolesFromJwt(token);
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

                UserAuthentication authentication = new UserAuthentication(memberId.toString(), null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (jwtTokenProvider.validateToken(token) == EXPIRED_JWT_TOKEN) {
                // 토큰이 만료된 경우 CustomException 던짐
                throw new CustomException(ErrorCode.ACCESS_TOKEN_EXPIRED);
            }

            // 다음 필터로 요청 전달
            filterChain.doFilter(request, response);

        } catch (CustomException e) {
            // CustomException을 잡아서 클라이언트에게 응답
            handleException(response, e.getErrorCode());
        }
    }

    // 예외 처리 및 응답
    private void handleException(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json; charset=UTF-8");

        // ObjectMapper는 예외 처리시에만 생성하여 메모리 최적화
        ObjectMapper objectMapper = new ObjectMapper();

        // 실패 응답 생성
        ApiResponseDto<?> apiResponse = ApiResponseDto.fail(errorCode);

        // 응답을 JSON 형식으로 변환 후 출력
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }

    // JWT를 요청 헤더에서 추출
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring("Bearer ".length());
        }
        return null;
    }
}
