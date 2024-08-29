package com.pickple.server.global.common.annotation.valid;

import com.pickple.server.global.common.annotation.MinSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;

public class MinSizeValidator implements ConstraintValidator<MinSize, List<String>> {
    private int minSize;

    @Override
    public void initialize(MinSize constraintAnnotation) {
        this.minSize = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 리스트가 null인 경우는 별도의 @NotNull로 검사해야 함
        }

        // 유효한 요소의 개수를 셈
        long validCount = value.stream()
                .filter(element -> element != null && !element.trim().isEmpty())
                .count();

        // 유효한 요소의 개수가 최소 크기보다 크거나 같은지 확인
        return validCount >= minSize;
    }
}
