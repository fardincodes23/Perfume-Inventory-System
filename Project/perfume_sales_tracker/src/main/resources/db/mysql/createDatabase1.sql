-- Drop and create database
DROP DATABASE IF EXISTS cis2232_bus_pass;
CREATE DATABASE cis2232_perfume_sales_track;
USE cis2232_perfume_sales_track;

-- ========================
-- CodeType Table
-- ========================
CREATE TABLE CodeType
(
    codeTypeId         INT(3)       NOT NULL COMMENT 'Primary key for code types',
    englishDescription VARCHAR(100) NOT NULL COMMENT 'English description',
    frenchDescription  VARCHAR(100) DEFAULT NULL COMMENT 'French description',
    createdDateTime    DATETIME     DEFAULT NULL,
    createdUserId      VARCHAR(20)  DEFAULT NULL,
    updatedDateTime    DATETIME     DEFAULT NULL,
    updatedUserId      VARCHAR(20)  DEFAULT NULL,
    PRIMARY KEY (codeTypeId)
) COMMENT ='Holds code types available for the application';

-- Insert CodeTypes
INSERT INTO CodeType (codeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (1, 'User Types', 'User Types FR', SYSDATE(), 'system', CURRENT_TIMESTAMP, 'system'),
       (2, 'Pass Types', 'Pass Types FR', SYSDATE(), 'system', CURRENT_TIMESTAMP, 'system');

-- ========================
-- CodeValue Table
-- ========================
CREATE TABLE CodeValue
(
    codeTypeId              INT(3)       NOT NULL COMMENT 'FK to CodeType',
    codeValueSequence       INT(3)       NOT NULL,
    englishDescription      VARCHAR(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort VARCHAR(20)  NOT NULL COMMENT 'English abbreviation',
    frenchDescription       VARCHAR(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  VARCHAR(20)  DEFAULT NULL COMMENT 'French abbreviation',
    sortOrder               INT(3)       DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         DATETIME     DEFAULT NULL,
    createdUserId           VARCHAR(20)  DEFAULT NULL,
    updatedDateTime         DATETIME     DEFAULT NULL,
    updatedUserId           VARCHAR(20)  DEFAULT NULL,
    PRIMARY KEY (codeTypeId, codeValueSequence)
) COMMENT ='Holds code values for the application';

-- Insert CodeValues
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', NOW(), 'admin', NOW(), 'admin'),
       (1, 2, 'Admin', 'Admin', 'AdminFR', 'AdminFR', NOW(), 'admin', NOW(), 'admin'),
       (2, 3, 'Regular', 'Regular', 'RegularFR', 'RegularFR', NOW(), 'admin', NOW(), 'admin'),
       (2, 4, 'K12', 'K12', 'K12FR', 'K12FR', NOW(), 'admin', NOW(), 'admin'),
       (2, 5, 'Student', 'Student', 'StudentFR', 'StudentFR', NOW(), 'admin', NOW(), 'admin'),
       (2, 6, 'Senior', 'Senior', 'SeniorFR', 'SeniorFR', NOW(), 'admin', NOW(), 'admin');

-- ========================
-- UserAccess Table
-- ========================
CREATE TABLE UserAccess
(
    userAccessId         INT(3)       NOT NULL AUTO_INCREMENT,
    username             VARCHAR(100) NOT NULL COMMENT 'Unique username for app',
    password             VARCHAR(128) NOT NULL,
    name                 VARCHAR(128),
    userAccessStatusCode INT(3)       NOT NULL DEFAULT 1 COMMENT 'CodeValue FK (status)',
    userTypeCode         INT(3)       NOT NULL DEFAULT 1 COMMENT 'CodeValue FK (user type)',
    createdDateTime      DATETIME              DEFAULT NULL COMMENT 'When user was created',
    PRIMARY KEY (userAccessId)
) COMMENT ='Holds users for the application';

-- Insert at least one admin user
INSERT INTO UserAccess (username, password, name, userAccessStatusCode, userTypeCode, createdDateTime)
VALUES ('admin', 'admin123', 'System Admin', 1, 2, NOW());

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
