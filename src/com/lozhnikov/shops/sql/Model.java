package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.entities.Field;
import com.lozhnikov.shops.entities.Row;
import com.lozhnikov.shops.entities.Table;
import com.lozhnikov.shops.entities.Value;

import java.util.ArrayList;
import java.util.List;

public class Model {
    public final static List<Table> tables = new ArrayList<Table>() {
        {
            add(new Table(
                    "stores",
                    "Торговые точки",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", "integer", true, true));
                            add(new Field("name", "Название", "varchar2(255)", true, false));
                            add(new Field("type_id", "ID типа", "integer", true, false));
                            add(new Field("area", "Площадь", "float(2)", false, false));
                            add(new Field("rent", "Арендная плата", "float(2)", false, false));
                            add(new Field("utility_bills", "Плата за коммунальные услуги", "float(2)", false, false));
                            add(new Field("number_of_counters", "Количество прилавков", "integer", false, false));
                        }
                    },
                    new ArrayList<Row>() {
                        {
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 1));
                                    add(new Value("name", "'ТЦ'"));
                                    add(new Value("type_id", 2));
                                }
                            }));
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 2));
                                    add(new Value("name", "'Быстроном'"));
                                    add(new Value("type_id", 1));
                                }
                            }));
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 3));
                                    add(new Value("name", "'Дядя Денер'"));
                                    add(new Value("type_id", 3));
                                }
                            }));
                        }
                    }));

            add(new Table(
                    "employees",
                    "Сотрудники",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", "integer", true, true));
                            add(new Field("full_name", "ФИО", "varchar2(255)", true, false));
                            add(new Field("position_id", "ID должности", "integer", true, false));
                            add(new Field("store_id", "ID торговой точки", "integer", true, false));
                            add(new Field("section_id", "ID секции", "integer", false, false));
                            add(new Field("salary", "Зарплата", "float(2)", true, false));
                        }
                    },
                    new ArrayList<Row>() {

                    }));

            add(new Table(
                    "store_types",
                    "Типы торговых точек",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", "integer", true, true));
                            add(new Field("name", "Название", "varchar2(255)", true, false));
                        }
                    },
                    new ArrayList<Row>() {
                        {
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 1));
                                    add(new Value("name", "'Универмаг'"));
                                }
                            }));
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 2));
                                    add(new Value("name", "'Магазин'"));
                                }
                            }));
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 3));
                                    add(new Value("name", "'Киоск'"));
                                }
                            }));
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 4));
                                    add(new Value("name", "'Лоток'"));
                                }
                            }));
                        }
                    }));

            add(new Table(
                    "positions",
                    "Должности",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", "integer", true, true));
                            add(new Field("name", "Название", "varchar2(255)", true, false));
                        }
                    },
                    new ArrayList<Row>() {
                        {
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 1));
                                    add(new Value("name", "'Продавец'"));
                                }
                            }));
                            add(new Row(new ArrayList<Value>() {
                                {
                                    add(new Value("id", 2));
                                    add(new Value("name", "'Менеджер'"));
                                }
                            }));
                        }
                    }));
        }
    };
}
