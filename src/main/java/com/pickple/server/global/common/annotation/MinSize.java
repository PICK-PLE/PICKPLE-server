package com.pickple.server.global.common.annotation;

import com.pickple.server.global.common.annotation.valid.MinSizeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinSizeValidator.class)
public @interface MinSize {
    String message() default "최소 {value}개의 요소가 있어야 합니다.";

    int value() default 1;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
