package com.pickple.server.api;

import com.pickple.server.global.exception.CustomException;
import com.pickple.server.global.response.enums.ErrorCode;
import com.pickple.server.global.response.ApiResponse;
import com.pickple.server.global.response.enums.SuccessCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/responseEntity")
    public ResponseEntity<String> responseEntity() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/success1")
    public ApiResponse<String> successWithData() {
        return ApiResponse.success(SuccessCode.TEST_OK_SUCCESS, "data");
    }

    @GetMapping("/success2")
    public ApiResponse<Void> successWithoutData() {
        return ApiResponse.success(SuccessCode.TEST_CREATE_SUCCESS);
    }


    @GetMapping("/exception1")
    public ApiResponse<Void> testCustomException() {
        return ApiResponse.fail(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/exception2")
    public ApiResponse<Void> testException() {
        throw new RuntimeException("This is a test exception");
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Void>> handleCustomException(CustomException ex) {
        ApiResponse<Void> response = ApiResponse.fail(ex.getErrorCode());
        return new ResponseEntity<>(response, ex.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleRuntimeException(RuntimeException ex) {
        ApiResponse<Void> response = ApiResponse.fail(ErrorCode.TEST_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

