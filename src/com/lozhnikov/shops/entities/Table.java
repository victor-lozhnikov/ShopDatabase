package com.lozhnikov.shops.entities;

import java.util.List;

public class Table {
    private final String name;
    private final String translate;
    private final List<Field> fields;
    private final List<Row> rows;

    public Table(String name, String translate, List<Field> fields, List<Row> rows) {
        this.name = name;
        this.translate = translate;
        this.fields = fields;
        this.rows = rows;
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

    public List<Row> getRows() {
        return rows;
    }
}
