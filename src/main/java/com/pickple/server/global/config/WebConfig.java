package com.pickple.server.global.config;

import com.pickple.server.global.common.annotation.resolver.GuestIdResolver;
import com.pickple.server.global.common.annotation.resolver.HostIdResolver;
import com.pickple.server.global.common.annotation.resolver.UserIdResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final UserIdResolver userIdResolver;
    private final GuestIdResolver guestIdResolver;
    private final HostIdResolver hostIdResolver;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080", "http://localhost:8081", "http://localhost:5173",
                        "https://pick-ple.com:5173", "https://pick-ple.com")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
                .allowedHeaders("*")
                .maxAge(3000);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userIdResolver);
        resolvers.add(guestIdResolver);
        resolvers.add(hostIdResolver);
    }
}