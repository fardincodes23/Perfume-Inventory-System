DROP DATABASE IF EXISTS cis2232_bus_pass;
CREATE DATABASE cis2232_bus_pass;
use cis2232_bus_pass;

CREATE TABLE CodeType (codeTypeId int(3) COMMENT 'This is the primary key for code types',
  englishDescription varchar(100) NOT NULL COMMENT 'English description',
  frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
  createdDateTime datetime DEFAULT NULL,
  createdUserId varchar(20) DEFAULT NULL,
  updatedDateTime datetime DEFAULT NULL,
  updatedUserId varchar(20) DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
(1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 'Pass Types', 'Pass Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');

CREATE TABLE CodeValue (
  codeTypeId int(3) NOT NULL COMMENT 'see code_type table',
  codeValueSequence int(3) NOT NULL,
  englishDescription varchar(100) NOT NULL COMMENT 'English description',
  englishDescriptionShort varchar(20) NOT NULL COMMENT 'English abbreviation for description',
  frenchDescription varchar(100) DEFAULT NULL COMMENT 'French description',
  frenchDescriptionShort varchar(20) DEFAULT NULL COMMENT 'French abbreviation for description',
  sortOrder int(3) DEFAULT NULL COMMENT 'Sort order if applicable',
  createdDateTime datetime DEFAULT NULL,
  createdUserId varchar(20) DEFAULT NULL,
  updatedDateTime datetime DEFAULT NULL,
  updatedUserId varchar(20) DEFAULT NULL
) COMMENT='This will hold code values for the application.';

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
(1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
(1, 2, 'Admin', 'Admin', 'Admin', 'Admin', '2015-10-25 18:44:37', 'admin', '2015-10-25 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 3, 'Regular', 'Regular', 'RegularFR', 'RegularFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 4, 'K12', 'K12', 'K12FR', 'K12FR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 5, 'Student', 'Student', 'StudentFR', 'StudentFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription, frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId) VALUES
    (2, 6, 'Senior', 'Senior', 'SeniorFR', 'SeniorFR', '2024-09-13 18:44:37', 'admin', '2024-09-13 18:44:37', 'admin');

CREATE TABLE UserAccess (
  userAccessId int(3) NOT NULL,
  username varchar(100) NOT NULL COMMENT 'Unique user name for app',
  password varchar(128) NOT NULL,
  name varchar(128),
  userAccessStatusCode int(3) NOT NULL DEFAULT '1' COMMENT 'Code type #2',
  userTypeCode int(3) NOT NULL DEFAULT '1' COMMENT 'Code type #1',
  createdDateTime datetime DEFAULT NULL COMMENT 'When user was created.'
);

ALTER TABLE CodeType
  ADD PRIMARY KEY (codeTypeId);

ALTER TABLE CodeValue
  ADD PRIMARY KEY (codeValueSequence);
--  ADD KEY codeTypeId (codeTypeId);

ALTER TABLE UserAccess
  ADD PRIMARY KEY (userAccessId),
  ADD KEY userTypeCode (userTypeCode);

ALTER TABLE CodeType
  MODIFY codeTypeId int(3) NOT NULL COMMENT 'This is the primary key for code types';

ALTER TABLE CodeValue
  MODIFY codeValueSequence int(3) NOT NULL;

ALTER TABLE UserAccess
  MODIFY userAccessId int(3) NOT NULL AUTO_INCREMENT;

CREATE TABLE BusPass(
id int(5),
name varchar(20) NOT NULL COMMENT 'Customer name',
address varchar(50) NOT NULL  COMMENT 'Address',
city varchar(20) NOT NULL COMMENT 'City',
preferredRoute varchar(10) NOT NULL COMMENT 'Preferred bus route',
passType int(3) NOT NULL COMMENT 'Code Type 2',
validForRuralRoute boolean NOT NULL  COMMENT 'Rural route option true/false',
lengthOfPass int(4) NOT NULL default 0 COMMENT 'Number of days for the pass',
startDate varchar(10) NOT NULL COMMENT 'Start date for pass',
cost decimal(6,2) NOT NULL  COMMENT 'Cost of pass'
) COMMENT 'This table holds bus pass data';

ALTER TABLE BusPass
  ADD PRIMARY KEY (id);
ALTER TABLE BusPass
  MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key', AUTO_INCREMENT=1;

INSERT INTO buspass (id, name, address, city, preferredRoute, passType, validForRuralRoute, lengthOfPass, startDate, cost)
VALUES
    (1, 'Muhammad Fayyaz', '123 There Dr.', 'Charlottetown', '1', 3, 1, 31, '2024-10-01', 35.50),
    (2, 'Marcie Henderson', '123 There Dr.', 'Charlottetown', '3', 3, 1, 365, '2024-10-01', 202.50),
    (3, 'Jappreet Singh', '222 Some St.', 'Stratford', '2', 5, 1, 7, '2024-10-04', 13.60),
    (4, 'Bob', '111 Grafton', 'Charlottetown', '7', 5, 1, 31, '2024-11-01', 28.40),
    (5, 'Landyn', '23 Main Street', 'Charlottetown', '7', 3, 1, 365, '2024-11-15', 202.50),
    (6, 'Joe', '2 DRive', 'Stratford', '3', 6, 1, 40, '2024-11-15', 30.00),
    (7, 'Anna Bell', '444 High Rd.', 'Summerside', '5', 2, 1, 60, '2024-10-20', 55.20),
    (8, 'James Cooper', '57 Ocean Ave.', 'Cornwall', '8', 4, 0, 14, '2024-10-15', 22.10),
    (9, 'Sophia Adams', '233 Maple St.', 'Charlottetown', '1', 3, 1, 120, '2024-09-10', 140.00),
    (10, 'David Green', '79 Pine St.', 'Stratford', '4', 2, 1, 15, '2024-11-05', 18.00),
    (11, 'Emily Davis', '123 East Blvd.', 'Charlottetown', '6', 1, 1, 30, '2024-10-07', 50.00),
    (12, 'William Brown', '678 King Rd.', 'Summerside', '5', 4, 1, 90, '2024-08-30', 100.00),
    (13, 'Olivia White', '99 Water St.', 'Stratford', '2', 3, 1, 365, '2024-11-12', 225.00),
    (14, 'Liam Wilson', '150 West Dr.', 'Charlottetown', '1', 5, 0, 7, '2024-09-20', 12.00),
    (15, 'Isabella Martinez', '202 Sunset Blvd.', 'Cornwall', '3', 6, 1, 365, '2024-10-25', 250.00),
    (16, 'Mason Lewis', '501 Grand Ave.', 'Summerside', '7', 2, 1, 90, '2024-10-11', 65.40),
    (17, 'Lucas Johnson', '22 Cedar Ln.', 'Stratford', '2', 4, 0, 30, '2024-10-30', 55.90),
    (18, 'Chloe Clark', '35 Birch St.', 'Charlottetown', '3', 1, 1, 60, '2024-11-10', 45.60),
    (19, 'Ethan Hall', '442 Oak Dr.', 'Charlottetown', '5', 3, 1, 365, '2024-10-15', 200.00),
    (20, 'Madeline Turner', '810 Forest Rd.', 'Cornwall', '6', 1, 1, 14, '2024-11-05', 28.30),
    (21, 'Aiden Scott', '345 Pinewood Ave.', 'Summerside', '7', 5, 0, 30, '2024-10-01', 32.70),
    (22, 'Charlotte Harris', '111 River Ln.', 'Stratford', '1', 6, 1, 60, '2024-09-25', 58.20),
    (23, 'Benjamin Allen', '800 Lakeview Dr.', 'Charlottetown', '2', 4, 1, 365, '2024-11-01', 225.00),
    (24, 'Lily Walker', '67 Park Ave.', 'Summerside', '3', 2, 1, 7, '2024-10-10', 12.50),
    (25, 'Henry King', '34 Elm St.', 'Cornwall', '4', 5, 1, 180, '2024-10-20', 125.00)