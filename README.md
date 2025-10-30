# Project: Perfume Sales track



### Development Team

* Lead Developer: Fardin Sahriar AL Rafat
* Business Client: Farhan Farhan
* Quality Control : Chris Hopkins

### Description

* This is a console based java application for tracking perfume sales. It allows users to select their choice of perfume from two different sizes. The program calculates subtotal, the tax amount and the total. \*

### Color

* Main Color: darkgoldenrod
* Secondary: darkblue
* Accent: darkred

### Required Fileds

* -id: int //Primary key.An unique id number for every transactions
* -transactionDate: String Note: yyyy-MM-dd // Date of the transaction
* -customerName: String
* -phoneNumber: String
* -perfumeChoice: String
* -perfumeSize: String //large(120ml) and medium bottle (90ml)
* -pricePerBottle: int // For large(120ml) $60 and medium (90ml) $40
* -quantity: int
* -subTotal: double //Cost before tax
* -taxAmount: double
* -total: double //Total with tax



### Calculation

* Constant Values:
* TAX\_AMOUNT = 0.10
* MEDIUM\_SIZE\_BOTTLE = 40
* LARGE\_SIZE\_BOTTLE = 60



* subtotal = quantity \* pricePerBottle
* taxAmount = subtotal \* 0.10
* totalPrice = subTotal +taxAmount



### Report Details

The user will enter a perfume name, and the program will output the list of the particular perfumes that were sold.
The list will be in descending order by order id (primary key), and also mention the quantity sold in each order.

