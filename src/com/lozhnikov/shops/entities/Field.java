package com.lozhnikov.shops.entities;

public class Field {
    private final String name;
    private final String translate;
    private final boolean isNotNull;
    private final boolean isString;

    public Field(String name, String translate, boolean isNotNull, boolean isString) {
        this.name = name;
        this.translate = translate;
        this.isNotNull = isNotNull;
        this.isString = isString;
    }

    public String getName() {
        return name;
    }

    public String getTranslate() {
        return translate;
    }

    public boolean isNotNull() {
        return isNotNull;
    }

    public boolean isString() {
        return isString;
    }
}
