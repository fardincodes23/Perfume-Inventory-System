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
(id, transactionDate, customerName, phoneNumber, perfumeChoice, perfumeSize, pricePerBottle, quantity, subTotal,
 taxAmount, total)
VALUES (1, '2025-10-01', 'Bj MacLean', '555-1234', 'Dior', '90ml', 40, 2, 80.00, 8.00, 88.00),
       (2, '2025-10-02', 'Donnie MacKinnon', '555-5678', 'Chanel', '120ml', 60, 1, 60.00, 6.00, 66.00),
       (3, '2025-10-03', 'Joey Kitson', '555-8765', 'Gucci', '90ml', 40, 3, 120.00, 12.00, 132.00),
       (4, '2025-10-04', 'Mike MacDonald', '555-4321', 'Dior', '120ml', 60, 2, 120.00, 12.00, 132.00),
       (5, '2025-10-05', 'Donnie Bowers', '555-2468', 'Chanel', '90ml', 40, 5, 200.00, 20.00, 220.00),
       (6, '2025-10-06', 'Gaylene Nicholson', '555-3344', 'Chanel', '90ml', 40, 2, 80.00, 8.00, 88.00),
       (7, '2025-10-07', 'Grace Nguyen', '555-7788', 'Dior', '120ml', 60, 1, 60.00, 6.00, 66.00),
       (8, '2025-10-08', 'Henry Adams', '555-9922', 'Gucci', '90ml', 40, 4, 160.00, 16.00, 176.00),
       (9, '2025-10-09', 'Isabella Moore', '555-4477', 'Dior', '120ml', 60, 3, 180.00, 18.00, 198.00),
       (10, '2025-10-10', 'James Wilson', '555-5566', 'Chanel', '90ml', 40, 2, 80.00, 8.00, 88.00),
       (11, '2025-10-11', 'Katherine Brown', '555-6677', 'Dior', '120ml', 60, 1, 60.00, 6.00, 66.00),
       (12, '2025-10-12', 'Liam Carter', '555-7789', 'Dior', '90ml', 40, 5, 200.00, 20.00, 220.00),
       (13, '2025-10-13', 'Mia Patel', '555-8800', 'Gucci', '120ml', 60, 2, 120.00, 12.00, 132.00),
       (14, '2025-10-14', 'Noah Green', '555-9911', 'Gucci', '90ml', 40, 1, 40.00, 4.00, 44.00),
       (15, '2025-10-15', 'Olivia Roberts', '555-2233', 'Dior', '120ml', 60, 4, 240.00, 24.00, 264.00);


-- Adding the optional CodeValue thing to the project. for this I have created this table below

-- 1. Create CodeType Table
CREATE TABLE CodeType (
                          codeTypeId int(3) NOT NULL COMMENT 'Primary Key',
                          englishDescription varchar(100) NOT NULL,
                          frenchDescription varchar(100) DEFAULT NULL,
                          createdDateTime datetime DEFAULT NULL,
                          createdUserId varchar(20) DEFAULT NULL,
                          updatedDateTime datetime DEFAULT NULL,
                          updatedUserId varchar(20) DEFAULT NULL,
                          PRIMARY KEY (CodeTypeId)
);

-- 2. Create CodeValue Table (With Composite Key)
CREATE TABLE CodeValue (
                           codeTypeId int(3) NOT NULL,
                           codeValueSequence int(3) NOT NULL,
                           englishDescription varchar(100) NOT NULL,
                           englishDescriptionShort varchar(20) NOT NULL,
                           frenchDescription varchar(100) DEFAULT NULL,
                           frenchDescriptionShort varchar(20) DEFAULT NULL,
                           sortOrder int(3) DEFAULT NULL,
                           createdDateTime datetime DEFAULT NULL,
                           createdUserId varchar(20) DEFAULT NULL,
                           updatedDateTime datetime DEFAULT NULL,
                           updatedUserId varchar(20) DEFAULT NULL,
                           PRIMARY KEY (codeTypeId, codeValueSequence)
);

-- 3. INSERT DATA
-- Let's say ID 100 is for "Perfume Brands"
INSERT INTO CodeType (codeTypeId, englishDescription, createdDateTime)
VALUES (100, 'Perfume Brands', NOW());

-- Now insert the specific brands linked to Type 100
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, sortOrder)
VALUES (100, 1, 'Chanel', 'Chanel', 1);

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, sortOrder)
VALUES (100, 2, 'Dior', 'Dior', 2);

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, sortOrder)
VALUES (100, 3, 'Gucci', 'Gucci', 3);