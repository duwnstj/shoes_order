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
    EMAIL_ALREADY_REGISTERED("이미 등록된 이메일입니다."),
    PASSWORD_NOT_MATCHED("비밀번호가 일치하지 않습니다."),
    NOT_USE_USER("사용 불가 아이디입니다."),
    INVALID_PASSWORD_FORMAT("비밀번호는 대소문자 포함 영문, 숫자, 특수문자를 최소 1글자씩 포함하며, 최소 8글자 이상이어야 합니다."),
    NOT_FOUND_USER_ADDRESS("등록되어 있는 유저 주소가 없습니다."),
    FORBIDDEN_ADDRESS_UPDATE("해당 주소 수정이 허용되지 않습니다."),
    FORBIDDEN_ADDRESS_DELETE("해당 주소 삭제가 허용되지 않습니다."),

    NOT_FOUND_STORE("매장를 찾을 수 없습니다."),

    NOT_FOUND_CART("장바구니 정보를 찾을 수 없습니다."),

    NOT_FOUND_ORDERS("주문 정보를 찾을 수 없습니다."),

    INVALID_ORDER_ACCESS("잘못된 주문 접근입니다.")
    ;

    private final String message;
}
