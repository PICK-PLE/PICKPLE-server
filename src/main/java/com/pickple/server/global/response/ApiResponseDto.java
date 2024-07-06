package com.pickple.server.global.response;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.pickple.server.global.response.enums.ErrorCode;
import com.pickple.server.global.response.enums.SuccessCode;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

@Builder
public record ApiResponseDto<T>(
        @JsonIgnore HttpStatus httpStatus,
        int status,
        @NonNull String message,
        @JsonInclude(value = NON_NULL) T data
) {
    public static <T> ApiResponseDto<T> success(final SuccessCode successCode, @Nullable final T data) {
        return ApiResponseDto.<T>builder()
                .httpStatus(successCode.getHttpStatus())
                .status(successCode.getCode())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiResponseDto<T> success(final SuccessCode successCode) {
        return ApiResponseDto.<T>builder()
                .httpStatus(successCode.getHttpStatus())
                .status(successCode.getCode())
                .message(successCode.getMessage())
                .data(null)
                .build();
    }

    public static <T> ApiResponseDto<T> fail(final ErrorCode errorCode) {
        return ApiResponseDto.<T>builder()
                .httpStatus(errorCode.getHttpStatus())
                .status(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }
}
