package com.lozhnikov.shops.entities;

import java.util.List;

public class Table {
    private final String name;
    private final String translate;
    private final List<Field> fields;

    public Table(String name, String translate, List<Field> fields) {
        this.name = name;
        this.translate = translate;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public String getTranslate() {
        return translate;
    }

    public List<Field> getFields() {
        return fields;
    }

    public Field findFieldByName(String name) {
        for (Field field : fields) {
            if (field.getTranslate().equals(name)) {
                return field;
            }
        }
        return null;
    }
}
