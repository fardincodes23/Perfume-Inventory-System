-- Drop and create database
DROP DATABASE IF EXISTS cis2232_perfume_sales_tracker;
CREATE DATABASE cis2232_perfume_sales_tracker;
USE cis2232_perfume_sales_tracker;

-- ========================
-- PerfumeTransaction Table
-- ========================
CREATE TABLE PerfumeTransaction
(
    id              INT(5)         NOT NULL AUTO_INCREMENT COMMENT 'Primary key',
    transactionDate VARCHAR(10)    NOT NULL COMMENT 'yyyy-MM-dd',
    customerName    VARCHAR(50)    NOT NULL,
    perfumeName     VARCHAR(50)    NOT NULL,
    quantity        INT(3)         NOT NULL,
    subTotal        DECIMAL(10, 2) NOT NULL,
    total           DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (id)
) COMMENT ='Stores perfume sales transactions';


ALTER TABLE PerfumeTransaction
    MODIFY id int(5) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

-- Sample Data
INSERT INTO PerfumeTransaction (id, transactionDate, customerName, perfumeName, quantity, subTotal, total)
VALUES (1, '2025-09-20', 'Alice Johnson', 'Rose Quartz', 2, 80.00, 88.00),
       (2, '2025-09-21', 'David Smith', 'Deep Charcoal', 1, 60.00, 66.00),
       (3, '2025-09-21', 'David Smith', 'Deep Charcoal', 8, 60.00, 66.00),
       (4, '2025-09-21', 'David Smith', 'Deep Charcoal', 1, 60.00, 66.00),
       (5, '2025-09-21', 'David Smith', 'Deep Charcoal', 1, 60.00, 66.00),
       (6, '2025-09-21', 'David Smith', 'Deep Charcoal', 44, 60.00, 66.00),
       (7, '2025-09-21', 'David Smith', 'Deep Charcoal', 1, 60.00, 66.00),
       (8, '2025-09-21', 'David Smith', 'Deep Charcoal', 1, 60.00, 66.00),
       (9, '2025-09-22', 'Emma Brown', 'Amber Bloom', 3, 120.00, 132.00);
