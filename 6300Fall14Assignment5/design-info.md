## **Ice Cream Cart Rewards Management System Design Information**

Contents
-----------------
- [Introduction](#introduction)
- [Nouns](#nouns)
- [Verbs](#verbs)
- [Classes and Attributes](#classes-and-attributes)
  - [Customer](#customer)
  - [Cart](#cart)
  - [Rewards DB](#rewards-db)
  - [Order History](#order-history)
- [Actions](#actions)
- [UML Draft](#uml-draft)
- [Author](#author)

### 1) Introduction
This document is meant as a supplement to show design decisions made when designing the UML 
diagram for the Ice Cream Cart Rewards Management System iteratively as they were made

![](http://i.imgur.com/3PF9lN8.png)

### 2) Nouns
The following list of nouns that I identified (highlighted in yellow above) 
``` text
-ice cream cart manager 
-purchases
-VIP customers -> name, address, birth date, and assigned VIP ID, status (gold)
-loyalty card -> shows their name and VIP ID
-Items: Ice Cream, Frozen Yogurt, Pre-order Icecream', free Ice Cream, Free frozen yogurt
-VIP points -> associated with VIP card ID, store by total and per month
-pre-order slots
-dates -> advance preorder weeks, current VIP points per month
```

### 3) Verbs
The following is a list of verbs corresponding to actions:
``` text
-manage/keep track: used to keep data about VIP customers
-sells/purchase/order
-earn/awarded
-calculate (vip totals)
-pre-order
-get's free ice cream
-accruing points (and double points)
-generate (daily report)
```

### 4) Classes and Attributes

Customer
``` 
Customer:
int VIP ID
string name
string address 
date bithdates
function to show vip ID 
bool gold_status

```

Cart
``` 
Ice Cream Cart:
function to sell (vip id) //also awards vip points
function to pre_order (date day);
function to int[7] preorder_ice_cream_slots 
```

Rewards DB
``` 
Rewards_bank:
int [][] points per vip id
int [][] monthly vip points per vip id
int [][] free_yogurts per vip id
int [][] discount per vip id
function to add_points
```

Order History
``` 
Order History:
string [] daily_sales_and_presales
function to report 
function to append to repord
```

### 5) Actions:

```
generate_report -> list of items
append_to_report (item sold/preordered) -> void
award_points ()
award_free_drink
award_discount
pre_order 
order
show_vip_id
assign_status
```

### 6) UML Draft
UML generated through markup language here:
http://yuml.me/edit/893a1bb0

![](http://yuml.me/893a1bb0)

```
[Customer|vipID:int;name:string; address:string; birthdate:date; goldStatus:bool |showID()] 
[iceCreamCart|inventory:sellableItems;inventory:preorderSlots |buy(); preorder()]
[Customer] -*orders-* > [iceCreamCart]
[sellableItem|sell();giveFree()] <>-> [iceCreamCart]

[frozenYogurt] <>-> [sellableItem]

[iceCream] <>-> [sellableItem]
[preorderItem|preorder();giveFree()] <> [iceCreamCart]
[frozenYogurt] <>-> [preorderItem]
[vipDB| addPoints(int); grantGold(vipID); awardFreeForGold()]<-*id number<>[vipID|points:int; acrualRate:double; monthlyPoints:int; freeItems:int; discountItems:int]
[vipDB]-.->[Customer]
[vipDB] <- [iceCreamCart]
[preorderItem]-.-[vipID]
[sellableItem]-.-[vipID]
[time]
[Report|report:arrayOfItems|generateReport(); appendToReport(item)] <- [sellableItem]
[Report] <- [preorderItem],
```
### 7) Author

| Name  				| GATECH Username		| E-mail						| Alias |
| --------------------- |:---------------------:|:-----------------------------:|:-----:| 
| [Alex Hortin](http://github.com/hortinstein) 	 		| ahortin3				| hortinstein@gmail.com  		| [AH](http://github.com/hortinstein )    |

