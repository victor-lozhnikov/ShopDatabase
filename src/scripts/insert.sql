insert into "positions" ("id", "name") values (1, 'Продавец');
insert into "positions" ("id", "name") values (2, 'Менеджер');

CREATE SEQUENCE "positions_seq" START WITH 3;

CREATE OR REPLACE TRIGGER "positions_on_insert"
    BEFORE INSERT
    ON "positions"
    FOR EACH ROW
    BEGIN
    SELECT "positions_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "store_types" values (1, 'Универмаг');
insert into "store_types" values (2, 'Магазин');
insert into "store_types" values (3, 'Киоск');
insert into "store_types" values (4, 'Лоток');

CREATE SEQUENCE "store_types_seq" START WITH 5;

CREATE OR REPLACE TRIGGER "store_types_on_insert"
    BEFORE INSERT
    ON "store_types"
    FOR EACH ROW
    BEGIN
    SELECT "store_types_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "stores" values (1, 'Быстроном', 1, 40000, 500, 20000);
insert into "stores" values (2, 'ТЦ', 2, 10000, 600, 15000);
insert into "stores" values (3, 'Дядя Денер', 4, 100, 300, 5000);
insert into "stores" values (4, 'Ярче', 2, 10000, 400, 10000);

CREATE SEQUENCE "stores_seq" START WITH 5;

CREATE OR REPLACE TRIGGER "stores_on_insert"
    BEFORE INSERT
    ON "stores"
    FOR EACH ROW
    BEGIN
    SELECT "stores_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "sections" values (1, 1, 'Кисломолочные продукты', 1);
insert into "sections" values (1, 2, 'Хлебобулочные изделия', 1);
insert into "sections" values (1, 3, 'Бытовая химия', 1);

insert into "products" values (1, 'Молоко');
insert into "products" values (2, 'Хлеб');
insert into "products" values (3, 'Макароны');
insert into "products" values (4, 'Колбаса');
insert into "products" values (5, 'Шоколад');
insert into "products" values (6, 'Пельмени');
insert into "products" values (7, 'Филе куриное');
insert into "products" values (8, 'Шаурма');

CREATE SEQUENCE "products_seq" START WITH 9;

CREATE OR REPLACE TRIGGER "products_on_insert"
    BEFORE INSERT
    ON "products"
    FOR EACH ROW
    BEGIN
    SELECT "products_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "employees" ("id", "full_name", "position_id", "store_id", "salary")
    values (1, 'Афанасьев Влад', 2, 4, 30000);
insert into "employees" ("id", "full_name", "position_id", "store_id", "salary")
    values (2, 'Владимиров Сергей', 1, 2, 18000);
insert into "employees" values (3, 'Лютаев Даниил', 1, 1, 1, 18000);
insert into "employees" values (4, 'Сухинский Константин', 1, 1, 2, 22000);
insert into "employees" ("id", "full_name", "position_id", "store_id", "salary")
    values (5, 'Марьин Алексей', 2, 3, 25000);

CREATE SEQUENCE "employees_seq" START WITH 6;

CREATE OR REPLACE TRIGGER "employees_on_insert"
    BEFORE INSERT
    ON "employees"
    FOR EACH ROW
    BEGIN
    SELECT "employees_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "buyers" values (1, 'Гуртуева Ольга');
insert into "buyers" values (2, 'Ложников Виктор');
insert into "buyers" values (3, 'Ботвинко Виталий');
insert into "buyers" values (4, 'Сараева Екатерина');
insert into "buyers" values (5, 'Мангараков Александр');
insert into "buyers" values (6, 'Дубков Марк');

CREATE SEQUENCE "buyers_seq" START WITH 7;

CREATE OR REPLACE TRIGGER "buyers_on_insert"
    BEFORE INSERT
    ON "buyers"
    FOR EACH ROW
    BEGIN
    SELECT "buyers_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "requests" values (1, 1, TO_DATE('2021/05/25 21:02:44', 'yyyy/mm/dd hh24:mi:ss'));

CREATE SEQUENCE "requests_seq" START WITH 2;

CREATE OR REPLACE TRIGGER "requests_on_insert"
    BEFORE INSERT
    ON "requests"
    FOR EACH ROW
    BEGIN
    SELECT "requests_seq".nextval
    INTO :new."id"
    FROM dual;
END;

insert into "products_in_request" values (1, 1, 20);
insert into "products_in_request" values (1, 2, 30);
insert into "products_in_request" values (1, 3, 25);

insert into "providers" values (1, 'Фабрика крутых продуктов');

insert into "availability_in_providers" values (1, 1, 100, 60);
insert into "availability_in_providers" values (1, 2, 100, 15);
insert into "availability_in_providers" values (1, 3, 100, 50);

CREATE SEQUENCE "deliveries_seq";

CREATE OR REPLACE TRIGGER "deliveries_on_insert"
    BEFORE INSERT
    ON "deliveries"
    FOR EACH ROW
    BEGIN
    SELECT "deliveries_seq".nextval
    INTO :new."id"
    FROM dual;
END;

CREATE SEQUENCE "providers_seq" START WITH 1;

CREATE OR REPLACE TRIGGER "providers_on_insert"
    BEFORE INSERT
    ON "providers"
    FOR EACH ROW
    BEGIN
    SELECT "providers_seq".nextval
    INTO :new."id"
    FROM dual;
END;

CREATE SEQUENCE "purchases_seq";

CREATE OR REPLACE TRIGGER "purchases_on_insert"
    BEFORE INSERT
    ON "purchases"
    FOR EACH ROW
    BEGIN
    SELECT "purchases_seq".nextval
    INTO :new."id"
    FROM dual;
END;