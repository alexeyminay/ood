package com.alexey.minay.ood.lab3

abstract class Beverage(
        private val description: String
): IBeverage {

    override fun getDescription() = description

}


open class Coffee(
        description: String = "Coffee"
): Beverage(description){

    override fun getCost() = 60

}


class Cappuccino(
        description: String = "Cappuccino"
): Coffee(description){

    override fun getCost() = 80

}


class Latte(
        description: String = "Latte"
): Coffee(description){

    override fun getCost() = 90

}


class Tea(
        description: String = "Tea"
): Beverage(description){

    override fun getCost() = 30

}


class Milkshake(
        description: String = "Milkshake"
): Beverage(description){

    override fun getCost() = 80

}