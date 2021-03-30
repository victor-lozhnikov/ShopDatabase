package com.lozhnikov.shops.entities;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private final List<Value> values;

    public Row() {
        values = new ArrayList<>();
    }

    public Row(List<Value> values) {
        this.values = values;
    }

    public List<Value> getValues() {
        return values;
    }

    public void add(Value value) {
        values.add(value);
    }
}
