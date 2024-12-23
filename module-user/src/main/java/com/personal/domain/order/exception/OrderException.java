package com.personal.domain.order.exception;

import com.personal.common.code.ResponseCode;
import com.personal.common.exception.custom.BadRequestException;

public class OrderException extends BadRequestException {
    public OrderException(ResponseCode code) {
        super(code);
    }
}
