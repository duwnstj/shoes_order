package com.personal.common.exception.custom;

import com.personal.common.code.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BadRequestException extends RuntimeException {

    private final int status;

    public BadRequestException(ResponseCode code) {
        super(code.getMessage());
        this.status = HttpStatus.BAD_REQUEST.value();
    }
}
