package com.alexey.minay.ood.lab3.coffee

abstract class CondimentDecorator(
        private val beverage: IBeverage
) : IBeverage {

    override fun getDescription() = "${beverage.getDescription()}, ${getCondimentDescription()}"

    override fun getCost() = beverage.getCost() + getCondimentCost()

    abstract fun getCondimentDescription(): String
    abstract fun getCondimentCost(): Int

}

class Cinnamon(
        beverage: IBeverage
) : CondimentDecorator(beverage) {
    override fun getCondimentDescription() = "cinnamon"

    override fun getCondimentCost() = 20
}


class Lemon(
        beverage: IBeverage,
        private val quantity: Int = 1
) : CondimentDecorator(beverage) {
    override fun getCondimentDescription() = "lemon x $quantity "

    override fun getCondimentCost() = 10 * quantity
}

enum class IceCubeType {
    DRY,
    WATER
}

class IceCube(
        beverage: IBeverage,
        private val quantity: Int = 1,
        private val type: IceCubeType = IceCubeType.WATER
) : CondimentDecorator(beverage) {
    override fun getCondimentDescription() =
            when (type) {
                IceCubeType.DRY -> "dry"
                IceCubeType.WATER -> "water"
            } + " ice cubes x $quantity"

    override fun getCondimentCost() =
            when (type) {
                IceCubeType.DRY -> 10
                IceCubeType.WATER -> 5
            } * quantity
}

enum class SyrupType {
    CHOCOLATE,
    MAPLE
}

class Syrup(
        beverage: IBeverage,
        private val type: SyrupType
) : CondimentDecorator(beverage) {
    override fun getCondimentDescription() =
            when (type) {
                SyrupType.CHOCOLATE -> "chocolate"
                SyrupType.MAPLE -> "maple"
            } + " syrup"

    override fun getCondimentCost() = 15
}

class ChocolateCrumbs(
        beverage: IBeverage,
        private val mass: Int
) : CondimentDecorator(beverage) {

    override fun getCondimentDescription() = "chocolate crumbs ${mass}g"

    override fun getCondimentCost() = 2 * mass

}


class CoconutFlakes(
        beverage: IBeverage,
        private val mass: Int
) : CondimentDecorator(beverage) {

    override fun getCondimentDescription() = "coconut flakes ${mass}g"

    override fun getCondimentCost() = 1 * mass

}

class Cream(
        beverage: IBeverage
) : CondimentDecorator(beverage){

    override fun getCondimentDescription() = "cream"

    override fun getCondimentCost() = 25

}

class Chocolate(
        beverage: IBeverage,
        private val count: Int = 1
) : CondimentDecorator(beverage){

    override fun getCondimentDescription() = "$count chocolate bar(s)"

    override fun getCondimentCost() = count * 10

}