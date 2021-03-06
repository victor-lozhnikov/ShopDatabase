ALTER TABLE "availability_in_stores" DROP CONSTRAINT "fk_availability_products";
ALTER TABLE "availability_in_stores" DROP CONSTRAINT "fk_availability_stores";
ALTER TABLE "deliveries" DROP CONSTRAINT "fk_delivery_provider";
ALTER TABLE "deliveries" DROP CONSTRAINT "fk_delivery_store";
ALTER TABLE "requests" DROP CONSTRAINT "fk_request_store";
ALTER TABLE "employees" DROP CONSTRAINT "fk_employee_store";
ALTER TABLE "employees" DROP CONSTRAINT "fk_employee_section";
ALTER TABLE "employees" DROP CONSTRAINT "fk_employee_position";
ALTER TABLE "products_in_delivery" DROP CONSTRAINT "fk_pd_deliveries";
ALTER TABLE "products_in_delivery" DROP CONSTRAINT "fk_pd_products";
ALTER TABLE "products_in_purchase" DROP CONSTRAINT "fk_pp_purchases";
ALTER TABLE "products_in_purchase" DROP CONSTRAINT "fk_pp_products";
ALTER TABLE "products_in_request" DROP CONSTRAINT "fk_pr_requests";
ALTER TABLE "products_in_request" DROP CONSTRAINT "fk_pr_products";
ALTER TABLE "purchases" DROP CONSTRAINT "fk_purchase_store";
ALTER TABLE "purchases" DROP CONSTRAINT "fk_purchase_buyer";
ALTER TABLE "sections" DROP CONSTRAINT "fk_section_store";
ALTER TABLE "stores" DROP CONSTRAINT "fk_store_type";
ALTER TABLE "availability_in_providers" DROP CONSTRAINT "fk_availability1_products";
ALTER TABLE "availability_in_providers" DROP CONSTRAINT "fk_availability1_providers";

ALTER TABLE "availability_in_stores" DROP PRIMARY KEY;
ALTER TABLE "availability_in_providers" DROP PRIMARY KEY;
ALTER TABLE "products_in_request" DROP PRIMARY KEY;
ALTER TABLE "products_in_delivery" DROP PRIMARY KEY;
ALTER TABLE "products_in_purchase" DROP PRIMARY KEY;
ALTER TABLE "sections" DROP PRIMARY KEY;
ALTER TABLE "buyers" DROP PRIMARY KEY;
ALTER TABLE "deliveries" DROP PRIMARY KEY;
ALTER TABLE "employees" DROP PRIMARY KEY;
ALTER TABLE "positions" DROP PRIMARY KEY;
ALTER TABLE "products" DROP PRIMARY KEY;
ALTER TABLE "providers" DROP PRIMARY KEY;
ALTER TABLE "purchases" DROP PRIMARY KEY;
ALTER TABLE "store_types" DROP PRIMARY KEY;
ALTER TABLE "stores" DROP PRIMARY KEY;
ALTER TABLE "requests" DROP PRIMARY KEY;

DROP TABLE "availability_in_stores";
DROP TABLE "buyers";
DROP SEQUENCE "buyers_seq";
DROP TABLE "deliveries";
DROP SEQUENCE "deliveries_seq";
DROP TABLE "employees";
DROP SEQUENCE "employees_seq";
DROP TABLE "positions";
DROP SEQUENCE "positions_seq";
DROP TABLE "products";
DROP SEQUENCE "products_seq";
DROP TABLE "products_in_delivery";
DROP TABLE "products_in_purchase";
DROP TABLE "providers";
DROP SEQUENCE "providers_seq";
DROP TABLE "purchases";
DROP SEQUENCE "purchases_seq";
DROP TABLE "sections";
DROP TABLE "store_types";
DROP SEQUENCE "store_types_seq";
DROP TABLE "stores";
DROP SEQUENCE "stores_seq";
DROP TABLE "requests";
DROP SEQUENCE "requests_seq";
DROP TABLE "products_in_request";
DROP TABLE "availability_in_providers";