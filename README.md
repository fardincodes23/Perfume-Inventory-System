# Project: Perfume Sales track #


### Development Team ###

* Lead Developer: Fardin Sahriar AL Rafat
* Business Client: Farhan Farhan
* Quality Control : Chris Hopkins

### Description ###

* This is a console based java application for tracking perfume sales. It allows users to select their choice of perfume from two different sizes. The program calculates subtotal, the tax amount and the total. *

### Color ###

* Main Color: #B76E79(Rose Quartz)
* Secondary: #F7E7CE(Soft Champagne)
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

### Calculation ###

* Constant Values:
* TAX_AMOUNT = 0.10
* MEDIUM_SIZE_BOTTLE = 40
* LARGE_SIZE_BOTTLE = 60


* subtotal = quantity * pricePerBottle
* taxAmount = subtotal * 0.10
* totalPrice = subTotal +taxAmount
