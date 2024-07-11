package com.pickple.server.global.common.annotation.resolver;

import com.pickple.server.api.guest.domain.Guest;
import com.pickple.server.api.guest.repository.GuestRepository;
import com.pickple.server.global.common.annotation.GuestId;
import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import java.security.Principal;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class GuestIdResolver implements HandlerMethodArgumentResolver {

    private final GuestRepository guestRepository;

    public GuestIdResolver(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(Long.class)
                && parameter.hasParameterAnnotation(GuestId.class);
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
        Guest guest = guestRepository.findGuestByUserId(Long.valueOf(principal.getName()));
        return guest.getId();
    }
}
