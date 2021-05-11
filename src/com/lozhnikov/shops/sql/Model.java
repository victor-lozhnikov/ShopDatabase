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
                            add(new Field("id", "ID", true, false));
                            add(new Field("name", "Название", true, true));
                            add(new Field("type_id", "ID типа", true, false));
                            add(new Field("area", "Площадь", true, false));
                            add(new Field("rent", "Арендная плата", true, false));
                            add(new Field("utility_bills", "Плата за коммунальные услуги", true, false));
                        }
                    }
            ));

            add(new Table(
                    "store_types",
                    "Типы торговых точек",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("name", "Название", true, true));
                        }
                    }
            ));

            add(new Table(
                    "sections",
                    "Секции",
                    new ArrayList<Field>() {
                        {
                            add(new Field("store_id", "ID магазина", true, false));
                            add(new Field("section_id", "ID секции", true, false));
                            add(new Field("name", "Название", true, true));
                            add(new Field("floor", "Этаж", false, false));
                        }
                    }
            ));

            add(new Table(
                    "employees",
                    "Сотрудники",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("full_name", "ФИ", true, true));
                            add(new Field("position_id", "ID должности", true, false));
                            add(new Field("store_id", "ID торговой точки", true, false));
                            add(new Field("section_id", "ID секции", false, false));
                            add(new Field("salary", "Зарплата", true, false));
                        }
                    }
            ));

            add(new Table(
                    "positions",
                    "Должности",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("name", "Название", true, true));
                        }
                    }
            ));

            add(new Table(
                    "products",
                    "Продукты",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("name", "Название", true, true));
                        }
                    }
            ));

            add(new Table(
                    "buyers",
                    "Покупатели",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("full_name", "ФИ", true, true));
                        }
                    }
            ));

            add(new Table(
                    "providers",
                    "Поставщики",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("full_name", "Название", true, true));
                        }
                    }
            ));

            add(new Table(
                    "availability",
                    "Наличие товаров",
                    new ArrayList<Field>() {
                        {
                            add(new Field("store_id", "ID магазина", true, false));
                            add(new Field("product_id", "ID продукта", true, false));
                            add(new Field("count", "Количество", true, false));
                            add(new Field("price", "Цена", true, false));
                        }
                    }
            ));

            add(new Table(
                    "deliveries",
                    "Поставки",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("store_id", "ID магазина", true, false));
                            add(new Field("provider_id", "ID поставщика", true, false));
                            add(new Field("date", "Дата", true, false));
                        }
                    }
            ));

            add(new Table(
                    "purchases",
                    "Покупки",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("store_id", "ID магазина", true, false));
                            add(new Field("buyer_id", "ID покупателя", false, false));
                            add(new Field("date", "Дата", true, false));
                        }
                    }
            ));

            add(new Table(
                    "requests",
                    "Заявки",
                    new ArrayList<Field>() {
                        {
                            add(new Field("id", "ID", true, false));
                            add(new Field("store_id", "ID магазина", true, false));
                            add(new Field("date", "Дата", true, false));
                        }
                    }
            ));

            add(new Table(
                    "products_in_delivery",
                    "Продукты в поставках",
                    new ArrayList<Field>() {
                        {
                            add(new Field("delivery_id", "ID поставки", true, false));
                            add(new Field("product_id", "ID продукта", true, false));
                            add(new Field("count", "Количество", true, false));
                            add(new Field("price", "Цена", true, false));
                        }
                    }
            ));

            add(new Table(
                    "products_in_purchase",
                    "Продукты в покупках",
                    new ArrayList<Field>() {
                        {
                            add(new Field("purchase_id", "ID покупки", true, false));
                            add(new Field("product_id", "ID продукта", true, false));
                            add(new Field("count", "Количество", true, false));
                            add(new Field("price", "Цена", true, false));
                        }
                    }
            ));

            add(new Table(
                    "products_in_requests",
                    "Продукты в заявках",
                    new ArrayList<Field>() {
                        {
                            add(new Field("request_id", "ID заявки", true, false));
                            add(new Field("product_id", "ID продукта", true, false));
                            add(new Field("count", "Количество", true, false));
                        }
                    }
            ));
        }
    };
}
