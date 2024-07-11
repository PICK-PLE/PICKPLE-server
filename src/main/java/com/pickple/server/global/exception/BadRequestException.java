package com.pickple.server.global.exception;

import com.pickple.server.global.response.enums.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
