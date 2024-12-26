package com.personal.domain.product.exception;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.BadRequestException;

public class ForbiddenProductsException extends BadRequestException {
    public ForbiddenProductsException(ResponseCode code) {
        super(code);
    }
}
