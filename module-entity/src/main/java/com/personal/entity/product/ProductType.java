package com.personal.entity.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum ProductType {
    PRODUCT(Type.PRODUCT),
    MATERIAL(Type.MATERIAL);

    private final String type;

    public static ProductType of(String type) {
        return Arrays.stream(ProductType.values())
                .filter(r -> r.name().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 ProductType"));
    }

    public static class Type {
        public static final String PRODUCT = "PRODUCT";
        public static final String MATERIAL = "MATERIAL";
    }
}
