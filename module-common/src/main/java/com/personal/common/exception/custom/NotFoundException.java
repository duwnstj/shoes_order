package com.personal.common.exception.custom;

import com.personal.common.code.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundException extends RuntimeException {

    private final int status;

    public NotFoundException(ResponseCode code) {
        super(code.getMessage());
        this.status = HttpStatus.NOT_FOUND.value();
    }
}
