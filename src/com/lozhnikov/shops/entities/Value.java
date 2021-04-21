package com.lozhnikov.shops.entities;

public class Value {
    private final Field field;
    private final Object value;

    public Value(Field field, Object value) {
        this.field = field;
        this.value = value;
    }

    public Field getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
