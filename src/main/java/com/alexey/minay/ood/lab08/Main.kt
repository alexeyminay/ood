package com.alexey.minay.ood.lab08

fun main() {
    val gumBallMachine = GumBallMachine(0)
    val menu = Menu()

    menu.addItem("help", "выход") { menu.showInstructions() }
    menu.addItem("refill", "заполнить автомат жвачкой") { input ->
        val gumBallCount = input.split(" ")[1].toIntOrNull()
        if (gumBallCount != null) {
            gumBallMachine.refill(gumBallCount)
        }
    }
    menu.addItem("exit", "выход") { menu.exit() }
    menu.addItem("insertQuarter", "добавить четвертак") {
        gumBallMachine.insertQuarter()
    }
    menu.addItem("ejectQuarters", "забрать монеты") {
        gumBallMachine.ejectQuarters()
    }
    menu.addItem("turnCrank", "дернуть рычаг") {
        gumBallMachine.turnCrank()
    }
    menu.addItem("printState", "напечатать информацию о состоянии автомата") {
        println(gumBallMachine)
    }

    menu.showInstructions()
    menu.run(::println)

}
