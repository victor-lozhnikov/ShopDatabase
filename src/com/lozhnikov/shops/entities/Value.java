package com.lozhnikov.shops.entities;

public class Value {
    private final String field;
    private final String value;

    public Value(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public String getValue() {
        return value;
    }
}
