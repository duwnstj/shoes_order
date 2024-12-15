package com.personal.common.entity;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessResponse<T> extends BaseResponse {
    private T data;

    public SuccessResponse(T data) {
        super(HttpStatus.OK.value(), "정상적으로 처리되었습니다.");
        this.data = data;
    }

    public static <T> SuccessResponse<T> of(T data) {
        return new SuccessResponse<>(data);
    }
}
