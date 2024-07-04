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
public record ApiResponse<T> (
        @JsonIgnore HttpStatus httpStatus,
        int status ,
        @NonNull String message,
        @JsonInclude(value = NON_NULL) T data
){
    public static<T> ApiResponse<T> success(final SuccessCode successCode, @Nullable final T data) {
        return ApiResponse.<T>builder()
                .httpStatus(successCode.getHttpStatus())
                .status(successCode.getCode())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }
    public static <T> ApiResponse<T> success(final SuccessCode successCode) {
        return ApiResponse.<T>builder()
                .httpStatus(successCode.getHttpStatus())
                .status(successCode.getCode())
                .message(successCode.getMessage())
                .data(null)
                .build();
    }
    public static <T> ApiResponse<T> fail(final ErrorCode errorCode) {
        return ApiResponse.<T>builder()
                .httpStatus(errorCode.getHttpStatus())
                .status(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(null)
                .build();
    }
}
