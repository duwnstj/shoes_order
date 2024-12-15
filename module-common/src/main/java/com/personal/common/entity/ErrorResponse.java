package com.personal.common.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse extends BaseResponse {
    private LocalDateTime dateTime;

    public ErrorResponse(int status, String message) {
        super(status, message);
        this.dateTime = LocalDateTime.now();
    }
}
