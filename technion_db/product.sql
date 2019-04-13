SET GLOBAL sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
SET SESSION sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';

DROP DATABASE IF EXISTS southwind;
CREATE DATABASE southwind;
USE southwind;

CREATE TABLE products (
         productID    INT UNSIGNED  NOT NULL AUTO_INCREMENT,
         productCode  CHAR(3)       NOT NULL DEFAULT '',
         name         VARCHAR(30)   NOT NULL DEFAULT '',
         quantity     INT UNSIGNED  NOT NULL DEFAULT 0,
         price        DECIMAL(7,2)  NOT NULL DEFAULT 99999.99,
         PRIMARY KEY  (productID)
       );
INSERT INTO products VALUES (1001, 'PEN', 'Pen Red', 5000, 1.23);
INSERT INTO products VALUES
         (NULL, 'PEN', 'Pen Blue',  8000, 1.25),
         (NULL, 'PEN', 'Pen Black', 2000, 1.25);
INSERT INTO products (productCode, name, quantity, price) VALUES
         ('PEC', 'Pencil 2B', 10000, 0.48),
         ('PEC', 'Pencil 2H', 8000, 0.49);
INSERT INTO products (productCode, name) VALUES ('PEC', 'Pencil HB');
SELECT * FROM products;
SELECT name, price FROM products WHERE price < 1.0;
SELECT name, quantity FROM products WHERE quantity <= 2000;
SELECT name, price FROM products WHERE productCode = 'PEN';
SELECT name, price FROM products WHERE name LIKE 'PENCIL%';
SELECT name, price FROM products WHERE name LIKE 'P__ %';
SELECT CONCAT(productCode, ' - ', name) AS `Product Description`, price FROM products;
SELECT DISTINCT price AS `DISTINCT Price` FROM products;
SELECT DISTINCT price, name FROM products;
SELECT * FROM products ORDER BY productCode, productID;
SELECT * FROM products GROUP BY productCode;
SELECT productCode, COUNT(*) AS Count FROM products GROUP BY productCode;
SELECT productCode, COUNT(*) AS count 
       FROM products 
       GROUP BY productCode
       ORDER BY count DESC;

SELECT MAX(price), MIN(price), AVG(price), STD(price), SUM(quantity)
       FROM products;
SELECT productCode, MAX(price) AS `Highest Price`, MIN(price) AS `Lowest Price`
       FROM products
       GROUP BY productCode;

SELECT productCode, MAX(price), MIN(price),
       CAST(AVG(price) AS DECIMAL(7,2)) AS `Average`,
       CAST(STD(price) AS DECIMAL(7,2)) AS `Std Dev`,
       SUM(quantity)
       FROM products
       GROUP BY productCode;


SELECT productCode AS `Product Code`,
       COUNT(*) AS `Count`,
       CAST(AVG(price) AS DECIMAL(7,2)) AS `Average`
       FROM products 
       GROUP BY productCode
       HAVING Count >=3;
       
SELECT productCode, 
          MAX(price), 
          MIN(price), 
          CAST(AVG(price) AS DECIMAL(7,2)) AS `Average`,
          SUM(quantity)
       FROM products
       GROUP BY productCode
       WITH ROLLUP;        

UPDATE products SET price = price * 1.1;
SELECT * FROM products;

UPDATE products SET quantity = quantity - 100 WHERE name = 'Pen Red';
SELECT * FROM products WHERE name = 'Pen Red';

UPDATE products SET quantity = quantity + 50, price = 1.23 WHERE name = 'Pen Red';
SELECT * FROM products WHERE name = 'Pen Red';

DELETE FROM products WHERE name LIKE 'Pencil%';
SELECT * FROM products;

DROP TABLE IF EXISTS suppliers;
   
CREATE TABLE suppliers (
    supplierID  INT UNSIGNED  NOT NULL AUTO_INCREMENT, 
    name        VARCHAR(30)   NOT NULL DEFAULT '', 
    phone       CHAR(8)       NOT NULL DEFAULT '',
    PRIMARY KEY (supplierID)
  );
   
DESCRIBE suppliers;

INSERT INTO suppliers VALUE
          (501, 'ABC Traders', '88881111'), 
          (502, 'XYZ Company', '88882222'), 
          (503, 'QQ Corp', '88883333');
   
SELECT * FROM suppliers;

ALTER TABLE products
       ADD COLUMN supplierID INT UNSIGNED NOT NULL;
DESCRIBE products;

UPDATE products SET supplierID = 501;
SELECT * FROM products;
ALTER TABLE products
       ADD FOREIGN KEY (supplierID) REFERENCES suppliers (supplierID);
 
DESCRIBE products;
SELECT * FROM products;

UPDATE products SET supplierID = 502 WHERE productID  = 1001;
SELECT * FROM products;
SELECT * FROM suppliers;

DESCRIBE products;

SELECT products.name, price, suppliers.name 
       FROM products 
          JOIN suppliers ON products.supplierID = suppliers.supplierID
       WHERE price < 0.6;


CREATE TABLE products_suppliers (
         productID   INT UNSIGNED  NOT NULL,
         supplierID  INT UNSIGNED  NOT NULL,
                     -- Same data types as the parent tables
         PRIMARY KEY (productID, supplierID),
                     -- uniqueness
         FOREIGN KEY (productID)  REFERENCES products  (productID),
         FOREIGN KEY (supplierID) REFERENCES suppliers (supplierID)
       );
   
DESCRIBE products_suppliers;

INSERT INTO products_suppliers VALUES (2001, 501), (2002, 501),
       (2003, 501), (2004, 502), (2001, 503);
-- Values in the foreign-key columns (of the child table) must match 
--   valid values in the columns they reference (of the parent table)
   
SELECT * FROM products_suppliers;
