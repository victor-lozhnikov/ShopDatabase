package com.lozhnikov.shops.entities;

public class Field {
    public String getName() {
        return name;
    }

    public String getTranslate() {
        return translate;
    }

    public String getType() {
        return type;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public boolean isPrimary() {
        return primary;
    }

    public Field(String name, String translate, String type, boolean notNull, boolean primary) {
        this.name = name;
        this.translate = translate;
        this.type = type;
        this.notNull = notNull;
        this.primary = primary;
    }

    private final String name;
    private final String translate;
    private final String type;
    private final boolean notNull;
    private final boolean primary;
}
