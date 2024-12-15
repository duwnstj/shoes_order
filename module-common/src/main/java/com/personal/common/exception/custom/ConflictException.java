package com.personal.common.exception.custom;

import com.personal.common.code.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ConflictException extends RuntimeException {

    private final int status;

    public ConflictException(ResponseCode code) {
        super(code.getMessage());
        this.status = HttpStatus.CONFLICT.value();
    }
}
