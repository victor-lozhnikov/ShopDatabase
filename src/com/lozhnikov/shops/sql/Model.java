package com.lozhnikov.shops.sql;

import com.lozhnikov.shops.entities.Field;
import com.lozhnikov.shops.entities.Table;

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
                            add(new Field("id", "ID", true));
                            add(new Field("name", "Название", true));
                            add(new Field("type_id", "ID типа", true));
                            add(new Field("area", "Площадь", true));
                            add(new Field("rent", "Арендная плата", true));
                            add(new Field("utility_bills", "Плата за коммунальные услуги", true));
                        }
                    }
            ));

            add(new Table(
                    "employees",
                    "Сотрудники",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("full_name", "ФИ", true));
                            add(new Field("position_id", "ID должности", true));
                            add(new Field("store_id", "ID торговой точки", true));
                            add(new Field("section_id", "ID секции", false));
                            add(new Field("salary", "Зарплата", true));
                        }
                    }
            ));

            add(new Table(
                    "store_types",
                    "Типы торговых точек",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("name", "Название", true));
                        }
                    }
            ));

            add(new Table(
                    "positions",
                    "Должности",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("name", "Название", true));
                        }
                    }
            ));

            add(new Table(
                    "sections",
                    "Секции",
                    new ArrayList<Field>() {
                        {
                            add(new Field("store_id", "ID магазина", true));
                            add(new Field("section_id", "ID секции", true));
                            add(new Field("name", "Название", true));
                            add(new Field("floor", "Этаж", false));
                        }
                    }
            ));

            add(new Table(
                    "buyers",
                    "Покупатели",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("full_name", "ФИ", true));
                        }
                    }
            ));

            add(new Table(
                    "availability",
                    "Наличие товаров",
                    new ArrayList<Field>() {
                        {
                            add(new Field("store_id", "ID магазина", true));
                            add(new Field("product_id", "ID продукта", true));
                            add(new Field("count", "Количество", true));
                            add(new Field("price", "Цена", true));
                        }
                    }
            ));

            add(new Table(
                    "deliveries",
                    "Поставки",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("store_id", "ID магазина", true));
                            add(new Field("provider_id", "ID поставщика", true));
                            add(new Field("date", "Дата", true));
                        }
                    }
            ));

            add(new Table(
                    "purchases",
                    "Покупки",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("store_id", "ID магазина", true));
                            add(new Field("buyer_id", "ID покупателя", false));
                            add(new Field("date", "Дата", true));
                        }
                    }
            ));

            add(new Table(
                    "products_in_delivery",
                    "Продукты в поставках",
                    new ArrayList<Field>() {
                        {
                            add(new Field("delivery_id", "ID поставки", true));
                            add(new Field("product_id", "ID продукта", true));
                            add(new Field("count", "Количество", true));
                            add(new Field("price", "Цена", true));
                        }
                    }
            ));

            add(new Table(
                    "products_in_purchase",
                    "Продукты в покупках",
                    new ArrayList<Field>() {
                        {
                            add(new Field("purchase_id", "ID покупки", true));
                            add(new Field("product_id", "ID продукта", true));
                            add(new Field("count", "Количество", true));
                            add(new Field("price", "Цена", true));
                        }
                    }
            ));

            add(new Table(
                    "providers",
                    "Поставщики",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("full_name", "Название", true));
                        }
                    }
            ));

            add(new Table(
                    "products",
                    "Продукты",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true));
                            add(new Field("full_name", "Название", true));
                        }
                    }
            ));
        }
    };
}
