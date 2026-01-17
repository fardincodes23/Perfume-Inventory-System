# Perfume Sales & Inventory Management System

## 📖 Description
This is a comprehensive Java application designed to track perfume sales transactions. Originally conceptualized as a console-based tool, it has been developed into a full-stack **Spring Boot** web application. 

The system allows users to select their choice of perfume and bottle size, automatically calculating financial totals including tax. It supports full CRUD operations, complex reporting, and enterprise-level API integration (REST & SOAP).

## Development Team
* **Lead Developer:** Fardin Sahriar AL Rafat
* **Business Client:** Farhan Farhan
* **Quality Control:** Chris Hopkins

---

## 🎨 Design & Color Scheme
The user interface follows a strict color palette for branding consistency:
* **Main Color:** `darkgoldenrod` (Primary headers and highlights)
* **Secondary Color:** `darkblue` (Navigation and structure)
* **Accent Color:** `darkred` (Error messages and alerts)

---

## 🛠 Tech Stack
* **Language:** Java
* **Framework:** Spring Boot (MVC)
* **Database:** MySQL
* **ORM:** Spring Data JPA / Hibernate
* **Frontend:** Thymeleaf, HTML5, Bootstrap
* **APIs:** REST (JSON) & SOAP (XML/WSDL)

---

## 📊 Data Model (Required Fields)
The application tracks the following fields for every transaction:

| Field Name | Data Type | Description |
| :--- | :--- | :--- |
| `id` | `int` | **Primary Key**. Unique ID for every transaction. |
| `transactionDate` | `String` | Format: `yyyy-MM-dd`. Records when the sale occurred. |
| `customerName` | `String` | Name of the purchaser. |
| `phoneNumber` | `String` | Customer contact number. |
| `perfumeChoice` | `String` | The specific brand or scent selected. |
| `perfumeSize` | `String` | **Options:** Large (120ml) or Medium (90ml). |
| `pricePerBottle` | `int/double`| **Standard:** Large ($60), Medium ($40). |
| `quantity` | `int` | Number of units purchased. |
| `subTotal` | `double` | Cost before tax. |
| `taxAmount` | `double` | Calculated tax value. |
| `total` | `double` | Final cost including tax. |

---

## 🧮 Business Logic & Calculations

### Constant Values
* `TAX_AMOUNT` (Rate) = **0.10** (10%)
* `MEDIUM_SIZE_BOTTLE` = **$40**
* `LARGE_SIZE_BOTTLE` = **$60**

### Financial Formulas
The application utilizes a specialized Business Object (BO) to handle these calculations automatically:
1.  **Subtotal:** `quantity * pricePerBottle`
2.  **Tax Amount:** `subtotal * 0.10`
3.  **Total Price:** `subTotal + taxAmount`

### Validation Rules
* **Bulk Order Policy:** If quantity is > 50, the price per bottle must be $100.00 or less.
* **Date Validation:** Transactions cannot be recorded with a future date.

---

## 📈 Reporting
The system includes file I/O capabilities for generating sales reports.

* **Perfume Choice Report:**
    * **Input:** User enters a perfume name.
    * **Output:** A list of all orders containing that specific perfume.
    * **Sorting:** The list is displayed in **descending order** by Order ID (Primary Key).
    * **Content:** Each entry details the transaction ID and the quantity sold.

---

## 🌐 API Services
This project demonstrates interoperability by exposing data via two protocols:

1.  **REST API (JSON):** `http://localhost:8080/api/PerfumeService/v1/transactions`
    * Supports standard CRUD (GET, POST, PUT, DELETE).
    
2.  **SOAP Web Service (XML):** `http://localhost:8083/perfumetransactionsoapservice?wsdl`
    * Legacy support using JAX-WS (Java 8) for retrieval by ID.

---

## 🚀 How to Run
1.  Clone the repository.
2.  Import the project as a Maven project.
3.  Update `application.properties` with your local MySQL credentials.
4.  **Run Server:** Execute `PerfumeApplication.java` to start the Spring Boot server.
5.  **Run SOAP Publisher:** Execute `PerfumeTransactionSoapServicePublisher.java` to start the SOAP server.
6.  **Run Clients:** * Navigate to `clients_that_use_web_services`.
    * Open `console_application_client_which_calls_rest` to run the REST consumer.
    * Open `test_console_app_that_calls_soap2` to run the SOAP consumer.
7.  **Access Web UI:** `http://localhost:8080/perfumetransaction`