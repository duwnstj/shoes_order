package com.personal.common.exception.custom;

import com.personal.common.code.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ForbiddenException extends RuntimeException {

    private final int status;

    public ForbiddenException(ResponseCode code) {
        super(code.getMessage());
        this.status = HttpStatus.FORBIDDEN.value();
    }
}
