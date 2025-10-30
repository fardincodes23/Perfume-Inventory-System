-- Drop and create database
DROP DATABASE IF EXISTS cis2232_perfume_sales_tracker;
CREATE DATABASE cis2232_perfume_sales_tracker;
USE cis2232_perfume_sales_tracker;

-- ========================
-- PerfumeTransaction Table
-- ========================
CREATE TABLE PerfumeTransaction
(
    id              INT(5),
    transactionDate VARCHAR(10)    NOT NULL COMMENT 'yyyy-MM-dd',
    customerName    VARCHAR(50)    NOT NULL COMMENT 'Name of the customer',
    phoneNumber     VARCHAR(50)    NOT NULL COMMENT 'Contact of the customer',
    perfumeChoice   VARCHAR(50)    NOT NULL COMMENT 'Selected perfume list',
    perfumeSize     VARCHAR(50)    NOT NULL COMMENT 'Size of the selected perfumes',
    pricePerBottle  INT(5)         NOT NULL,
    quantity        INT(3)         NOT NULL,
    subTotal        DECIMAL(10, 2) NOT NULL COMMENT 'Tota before Tax',
    taxAmount       DECIMAL(10, 2) NOT NULL COMMENT 'Tax amount',
    total           DECIMAL(10, 2) NOT NULL COMMENT 'Total after tax'

) COMMENT ='Stores perfume sales transactions';


ALTER TABLE PerfumeTransaction
    ADD PRIMARY KEY (id);

ALTER TABLE PerfumeTransaction
    MODIFY id int(5) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;


-- Sample Data
INSERT INTO PerfumeTransaction
(id, transactionDate, customerName, phoneNumber, perfumeChoice, perfumeSize, pricePerBottle, quantity, subTotal, taxAmount, total)
VALUES
    (1, '2025-10-01', 'Alice Johnson', '555-1234', 'Dior', '90ml', 40, 2, 80.00, 8.00, 88.00),
    (2, '2025-10-02', 'Bob Smith', '555-5678', 'Chanel', '120ml', 60, 1, 60.00, 6.00, 66.00),
    (3, '2025-10-03', 'Catherine Lee', '555-8765', 'Gucci', '90ml', 40, 3, 120.00, 12.00, 132.00),
    (4, '2025-10-04', 'David Park', '555-4321', 'Dior', '120ml', 60, 2, 120.00, 12.00, 132.00),
    (5, '2025-10-05', 'Ella Thompson', '555-2468', 'Chanel', '90ml', 40, 5, 200.00, 20.00, 220.00);
