CREATE TABLE "availability" (
    "store_id" NUMBER(11) NOT NULL,
    "product_id" NUMBER(11) NOT NULL,
    "count" NUMBER(11) NOT NULL,
    "price" NUMBER(11,2) NOT NULL,
    PRIMARY KEY ("store_id", "product_id")
);

CREATE TABLE "buyers" (
    "id" NUMBER(11) NOT NULL,
    "full_name" VARCHAR2(255) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "deliveries" (
    "id" NUMBER(11) NOT NULL,
    "store_id" NUMBER(11) NOT NULL,
    "provider_id" NUMBER(11) NOT NULL,
    "date" DATE NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "employees" (
    "id" NUMBER(11) NOT NULL,
    "full_name" VARCHAR2(255) NOT NULL,
    "position_id" NUMBER(11) NOT NULL,
    "store_id" NUMBER(11) NOT NULL,
    "section_id" NUMBER(11),
    "salary" NUMBER(11,2) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "positions" (
    "id" NUMBER(11) NOT NULL,
    "name" VARCHAR2(255) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "products" (
    "id" NUMBER(11) NOT NULL,
    "name" VARCHAR2(255) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "products_in_delivery" (
    "delivery_id" NUMBER(11) NOT NULL,
    "product_id" NUMBER(11) NOT NULL,
    "count" NUMBER(11) NOT NULL,
    "price" NUMBER(11,2) NOT NULL,
    PRIMARY KEY ("delivery_id", "product_id")
);

CREATE TABLE "products_in_purchase" (
    "purchase_id" NUMBER(11) NOT NULL,
    "product_id" NUMBER(11) NOT NULL,
    "count" NUMBER(11) NOT NULL,
    "price" NUMBER(11,2) NOT NULL,
    PRIMARY KEY ("purchase_id", "product_id")
);

CREATE TABLE "providers" (
    "id" NUMBER(11) NOT NULL,
    "name" VARCHAR2(255) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "purchases" (
    "id" NUMBER(11) NOT NULL,
    "store_id" NUMBER(11) NOT NULL,
    "buyer_id" NUMBER(11),
    "date" DATE NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "sections" (
    "store_id" NUMBER(11) NOT NULL,
    "section_id" NUMBER(11) NOT NULL,
    "name" VARCHAR2(255) NOT NULL,
    "floor" NUMBER(11),
    PRIMARY KEY ("store_id", "section_id")
);

CREATE TABLE "store_types" (
    "id" NUMBER(11) NOT NULL,
    "name" VARCHAR2(255) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "stores" (
    "id" NUMBER(11) NOT NULL,
    "name" VARCHAR2(255) NOT NULL,
    "type_id" NUMBER(11) NOT NULL,
    "area" NUMBER(11,2) NOT NULL,
    "rent" NUMBER(11,2) NOT NULL,
    "utility_bills" NUMBER(11,2) NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "requests" (
    "id" NUMBER(11) NOT NULL,
    "store_id" NUMBER(11) NOT NULL,
    "date" DATE NOT NULL,
    PRIMARY KEY ("id")
);

CREATE TABLE "products_in_request" (
    "request_id" NUMBER(11) NOT NULL,
    "product_id" NUMBER(11) NOT NULL,
    "count" NUMBER(11) NOT NULL,
    PRIMARY KEY ("request_id", "product_id")
);

ALTER TABLE "availability" ADD CONSTRAINT "fk_availability_products" FOREIGN KEY ("product_id") REFERENCES "products" ("id");
ALTER TABLE "availability" ADD CONSTRAINT "fk_availability_stores" FOREIGN KEY ("store_id") REFERENCES "stores" ("id");
ALTER TABLE "deliveries" ADD CONSTRAINT "fk_delivery_provider" FOREIGN KEY ("provider_id") REFERENCES "providers" ("id");
ALTER TABLE "deliveries" ADD CONSTRAINT "fk_delivery_store" FOREIGN KEY ("store_id") REFERENCES "stores" ("id");
ALTER TABLE "requests" ADD CONSTRAINT "fk_request_store" FOREIGN KEY ("store_id") REFERENCES "stores" ("id");
ALTER TABLE "employees" ADD CONSTRAINT "fk_employee_store" FOREIGN KEY ("store_id") REFERENCES "stores" ("id");
ALTER TABLE "employees" ADD CONSTRAINT "fk_employee_section" FOREIGN KEY ("store_id", "section_id") REFERENCES "sections" ("store_id", "section_id");
ALTER TABLE "employees" ADD CONSTRAINT "fk_employee_position" FOREIGN KEY ("position_id") REFERENCES "positions" ("id");
ALTER TABLE "products_in_delivery" ADD CONSTRAINT "fk_pd_deliveries" FOREIGN KEY ("delivery_id") REFERENCES "deliveries" ("id");
ALTER TABLE "products_in_delivery" ADD CONSTRAINT "fk_pd_products" FOREIGN KEY ("product_id") REFERENCES "products" ("id");
ALTER TABLE "products_in_purchase" ADD CONSTRAINT "fk_pp_purchases" FOREIGN KEY ("purchase_id") REFERENCES "purchases" ("id");
ALTER TABLE "products_in_purchase" ADD CONSTRAINT "fk_pp_products" FOREIGN KEY ("product_id") REFERENCES "products" ("id");
ALTER TABLE "products_in_request" ADD CONSTRAINT "fk_pr_requests" FOREIGN KEY ("request_id") REFERENCES "requests" ("id");
ALTER TABLE "products_in_request" ADD CONSTRAINT "fk_pr_products" FOREIGN KEY ("product_id") REFERENCES "products" ("id");
ALTER TABLE "purchases" ADD CONSTRAINT "fk_purchase_store" FOREIGN KEY ("store_id") REFERENCES "stores" ("id");
ALTER TABLE "purchases" ADD CONSTRAINT "fk_purchase_buyer" FOREIGN KEY ("buyer_id") REFERENCES "buyers" ("id");
ALTER TABLE "sections" ADD CONSTRAINT "fk_section_store" FOREIGN KEY ("store_id") REFERENCES "stores" ("id");
ALTER TABLE "stores" ADD CONSTRAINT "fk_store_type" FOREIGN KEY ("type_id") REFERENCES "store_types" ("id");