package com.alexey.minay.ood.lab3.streams

abstract class Beverage(
        private val description: String
) : IBeverage {

    override fun getDescription() = description

}


open class Coffee(
        description: String = "Coffee"
) : Beverage(description) {

    override fun getCost() = 60

}


class Cappuccino(
        private val portion: Portion = Portion.STANDARD,
        private val description: String = "cappuccino"
) : Coffee(description) {

    override fun getCost() = portion.cost

    override fun getDescription() = "${portion.description} $description"

    enum class Portion(val description: String, val cost: Int) {
        STANDARD("Standard", 80),
        DOUBLE("Double", 120)
    }

}


class Latte(
        private val portion: Portion = Portion.STANDARD,
        private val description: String = "latte"
) : Coffee(description) {

    override fun getCost() = portion.cost

    override fun getDescription() = " ${portion.description} $description"

    enum class Portion(val description: String, val cost: Int) {
        STANDARD("Standard", 90),
        DOUBLE("Double", 130)
    }

}


class Tea(
        private val grade: Grade = Grade.BLACK,
        private val description: String = "Tea"
) : Beverage(description) {

    override fun getCost() = 30

    override fun getDescription() = " ${grade.description} $description"

    enum class Grade(val description: String) {
        BLACK("black"),
        GREEN("green"),
        MILK_OOLONG("milk oolong"),
        PUER("puer")
    }

}


class Milkshake(
        private val portion: Portion = Portion.STANDARD,
        private val description: String = "milkshake"
) : Beverage(description) {

    override fun getCost() = portion.cost

    override fun getDescription() = "${portion.description} $description"

    enum class Portion(val description: String, val cost: Int) {
        LITTLE("Little", 50),
        STANDARD("Standard", 60),
        BIG("Big", 80)
    }

}