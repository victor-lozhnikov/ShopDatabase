insert into "positions" ("id", "name") values (1, 'Продавец');
insert into "positions" ("id", "name") values (2, 'Менеджер');

insert into "store_types" values (1, 'Универмаг');
insert into "store_types" values (2, 'Магазин');
insert into "store_types" values (3, 'Киоск');
insert into "store_types" values (4, 'Лоток');

insert into "stores" values (1, 'Быстроном', 1, 40000, 500, 20000);
insert into "stores" values (2, 'ТЦ', 2, 10000, 600, 15000);
insert into "stores" values (3, 'Дядя Денер', 4, 100, 300, 5000);
insert into "stores" values (4, 'Ярче', 2, 10000, 400, 10000);

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

insert into "employees" ("id", "full_name", "position_id", "store_id", "salary")
    values (1, 'Афанасьев Влад', 2, 4, 30000);
insert into "employees" ("id", "full_name", "position_id", "store_id", "salary")
    values (2, 'Владимиров Сергей', 1, 2, 18000);
insert into "employees" values (3, 'Лютаев Даниил', 1, 1, 1, 18000);
insert into "employees" values (4, 'Сухинский Константин', 1, 1, 2, 22000);
insert into "employees" ("id", "full_name", "position_id", "store_id", "salary")
    values (5, 'Марьин Алексей', 2, 3, 25000);

insert into "buyers" values (1, 'Гуртуева Ольга');
insert into "buyers" values (2, 'Ложников Виктор');
insert into "buyers" values (3, 'Ботвинко Виталий');
insert into "buyers" values (4, 'Сараева Екатерина');
insert into "buyers" values (5, 'Мангараков Александр');
insert into "buyers" values (6, 'Дубков Марк');