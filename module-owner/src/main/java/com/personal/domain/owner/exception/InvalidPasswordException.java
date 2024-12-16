package com.personal.domain.owner.exception;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.BadRequestException;

public class InvalidPasswordException extends BadRequestException {
    public InvalidPasswordException(ResponseCode code) {
        super(code);
    }
}
