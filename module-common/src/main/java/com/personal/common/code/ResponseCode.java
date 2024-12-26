package com.personal.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // 공통
    SUCCESS("정상 처리되었습니다."),


    // User Module Error
    NOT_FOUND_USER("유저를 찾을 수 없습니다."),
    UNAUTHORIZED_ACCESS("접근권한이 없습니다."),
    EMAIL_ALREADY_REGISTERED("이미 등록된 이메일입니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    NOT_USE_USER("사용 불가 아이디입니다."),
    INVALID_PASSWORD_FORMAT("비밀번호는 대소문자 포함 영문, 숫자, 특수문자를 최소 1글자씩 포함하며, 최소 8글자 이상이어야 합니다."),
    NOT_FOUND_USER_ADDRESS("등록되어 있는 유저 주소가 없습니다."),
    FORBIDDEN_ADDRESS_UPDATE("해당 주소 수정이 허용되지 않습니다."),
    FORBIDDEN_ADDRESS_DELETE("해당 주소 삭제가 허용되지 않습니다."),
    FORBIDDEN_PRODUCTS_ADD("해당 상품 등록이 허용되지 않습니다."),
    FORBIDDEN_STORES_UPDATE("해당 가게 수정이 허용되지 않습니다."),
    FORBIDDEN_STORES_DELETE("해당 가게 삭제가 허용되지 않습니다."),


    NOT_FOUND_STORE("매장을 찾을 수 없습니다.");

    private final String message;
}
