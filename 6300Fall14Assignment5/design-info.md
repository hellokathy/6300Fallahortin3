## **Ice Cream Cart Rewards Management System Design Information**

Contents
-----------------
- [Introduction](#introduction)
- [Nouns](#nouns)
- [Verbs](#verbs)
- [Author](#author)
- [Classes and Attributes](#classes-and-attributes)

### 1) Introduction
This document is meant as a supplement to show design decisions made when designing the UML diagram for the Ice Cream Cart Rewards Management System

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
```

Cart
```
Ice Cream Cart:
function to sell (vip id) //also awards vip points
int [][] points per vip id;
int [][] monthly vip points per vip id
preorder slots
```

### 4) Author

| Name  				| GATECH Username		| E-mail						| Alias |
| --------------------- |:---------------------:|:-----------------------------:|:-----:| 
| [Alex Hortin](http://github.com/hortinstein) 	 		| ahortin3				| hortinstein@gmail.com  		| [AH](http://github.com/hortinstein )    |

