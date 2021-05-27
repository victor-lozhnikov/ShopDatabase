CREATE USER shop_admin IDENTIFIED BY shop_admin;
GRANT CONNECT, RESOURCE TO shop_admin;
CREATE USER shop_manager IDENTIFIED BY shop_manager;
GRANT CONNECT, RESOURCE TO shop_manager;
CREATE USER shop_buyer IDENTIFIED BY shop_buyer;
GRANT CONNECT, RESOURCE TO shop_buyer;
