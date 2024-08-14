package com.pickple.server.global.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pickple.server.global.response.ApiResponseDto;
import com.pickple.server.global.response.enums.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        setResponse(response);
    }

    private void setResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // ApiResponseDto를 사용해 에러 응답 생성
        ApiResponseDto<?> apiResponse = ApiResponseDto.fail(ErrorCode.NOT_ADMIN);

        // JSON 응답으로 전송
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
    }
}
