package com.lozhnikov.shops.entities;

public class Value {
    private final String field;
    private final Object value;

    public Value(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
