package com.lozhnikov.shops.entities;

import java.util.List;

public class Row {
    private final List<Value> values;

    public Row(List<Value> values) {
        this.values = values;
    }

    public List<Value> getValues() {
        return values;
    }
}
