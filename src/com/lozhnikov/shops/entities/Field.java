package com.lozhnikov.shops.entities;

public class Field {
    private final String name;
    private final String translate;
    private final boolean notNull;

    public Field(String name, String translate, boolean notNull) {
        this.name = name;
        this.translate = translate;
        this.notNull = notNull;
    }

    public String getName() {
        return name;
    }

    public String getTranslate() {
        return translate;
    }

    public boolean isNotNull() {
        return notNull;
    }
}
