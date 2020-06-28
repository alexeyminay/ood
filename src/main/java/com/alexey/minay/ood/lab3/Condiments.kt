package com.alexey.minay.ood.lab3

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
    override fun getCondimentDescription() = "Cinnamon"

    override fun getCondimentCost() = 20
}


class Lemon(
        beverage: IBeverage,
        private val quantity: Int = 1
) : CondimentDecorator(beverage) {
    override fun getCondimentDescription() = "Lemon x $quantity "

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
                IceCubeType.DRY -> "Dry"
                IceCubeType.WATER -> "Water"
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
                SyrupType.CHOCOLATE -> "Chocolate"
                SyrupType.MAPLE -> "Maple"
            } + " syrup"

    override fun getCondimentCost() = 15
}

class ChocolateCrumbs(
        beverage: IBeverage,
        private val mass: Int
) : CondimentDecorator(beverage) {

    override fun getCondimentDescription() = "Chocolate crumbs ${mass}g"

    override fun getCondimentCost() = 2 * mass

}


class CoconutFlakes(
        beverage: IBeverage,
        private val mass: Int
) : CondimentDecorator(beverage) {

    override fun getCondimentDescription() = "Coconut flakes ${mass}g"

    override fun getCondimentCost() = 1 * mass

}