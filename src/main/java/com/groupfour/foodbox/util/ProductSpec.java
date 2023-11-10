package com.groupfour.foodbox.util;

public enum ProductSpec {
    NEW("신규"), BEST("추천");
    private final String value;
    private ProductSpec(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
