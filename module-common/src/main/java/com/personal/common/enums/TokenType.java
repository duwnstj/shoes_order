package com.personal.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS(60 * 60 * 24 * 1000L),
    REFRESH(60 * 60 * 24 * 1000L);

    private final long lifeTime;
}
