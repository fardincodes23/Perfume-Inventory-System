-- Drop and create database
DROP DATABASE IF EXISTS cis2232_perfume_sales_track;
CREATE DATABASE cis2232_perfume_sales_track;
USE cis2232_perfume_sales_track;

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

-- Sample Data
INSERT INTO PerfumeTransaction (transactionDate, customerName, perfumeName, quantity, subTotal, total)
VALUES ('2025-09-20', 'Alice Johnson', 'Rose Quartz', 2, 80.00, 88.00),
       ('2025-09-21', 'David Smith', 'Deep Charcoal', 1, 60.00, 66.00),
       ('2025-09-22', 'Emma Brown', 'Amber Bloom', 3, 120.00, 132.00);
