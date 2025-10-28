# Project: Perfume Sales track #


### Development Team ###

* Lead Developer: Fardin Sahriar AL Rafat
* Business Client: Farhan Farhan
* Quality Control : Chris Hopkins

### Description ###

* This is a console based java application for tracking perfume sales. It allows users to select their choice of perfume from two different sizes. The program calculates subtotal, the tax amount and the total. *

### Color ###

* Main Color: #B76E79(Rose Quartz)
* Secondary: #b47511
* ACcent: #333333(Deep Charcoal)

### Required Fileds ###

* -id: int //primary key
* -transactionDate: String Note: yyyy-MM-dd
* -customerName: String
* perfumeName: String
* quantity: int
* subTotal: double //cost before tax
* total: double //total with tax
* id: int // an unique id number for every transactions
* transactionDate: String // date needed to keep track of the transaction
* size: int

### Calculation ###

* Constant Values:
* TAX_AMOUNT = 0.10
* MEDIUM_SIZE_BOTTLE = 40
* LARGE_SIZE_BOTTLE = 60


* subtotal = quantity * pricePerBottle
* taxAmount = subtotal * 0.10
* totalPrice = subTotal +taxAmount


### Report Details ###

The user will enter a perfumes name, and the program will output the list of the particular perfumes that were sold.
The list will be in descending order by order id (primary key), and also mention the quantity sold in each order.
