package com.pickple.server.global.common.annotation.resolver;

import com.pickple.server.api.host.domain.Host;
import com.pickple.server.api.host.repository.HostRepository;
import com.pickple.server.global.common.annotation.HostId;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.security.Principal;
import lombok.AllArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@AllArgsConstructor
@Component
public class HostIdResolver implements HandlerMethodArgumentResolver {

    private final HostRepository hostRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class)
                && parameter.hasParameterAnnotation(HostId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        final Principal principal = webRequest.getUserPrincipal();
        if (principal == null) {
            throw new CustomException(ErrorCode.EMPTY_PRINCIPAL);
        }
        Host host = hostRepository.findHostByUserId(Long.valueOf(principal.getName()));
        if (host == null) {
            throw new CustomException(ErrorCode.HOST_NOT_FOUND);
        }
        return host.getId();
    }
}
