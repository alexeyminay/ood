package com.alexey.minay.ood.lab08

fun main(args: Array<String>) {
    printHelp()
    val gumBallMachine = GumBallMachine(0)
    var resumed = true
    while (resumed) {
        val input = readLine()
        val splittedInput = input?.split(" ")
        if (splittedInput?.size == 2 && splittedInput[0] == "refill") {
            val gumBallCount = splittedInput[1].toIntOrNull()
            if (gumBallCount == null) {
                println("gumBallCount should be a number")
                continue
            }
            gumBallMachine.refill(gumBallCount)
            continue
        }
        when (input) {
            "help" -> printHelp()
            "exit" -> resumed = false
            "insertQuarter" -> gumBallMachine.insertQuarter()
            "ejectQuarters" -> gumBallMachine.ejectQuarters()
            "turnCrank" -> gumBallMachine.turnCrank()
            "printState" -> println(gumBallMachine)
            else -> println("unknown command")
        }
    }
}

private fun printHelp() {
    println("""
        - exit - выход
        - help - помощь
        - insertQuarter - добавить четвертак
        - ejectQuarters - забрать монеты
        - turnCrank - дернуть рычаг
        - refill <count> - заполнить автомат жвачкой
        - printState - напечатать информацию о состоянии автомата
    """.trimIndent())
}

fun testGumBallMachine(gumBallMachine: IGumBallMachine) {
    println(gumBallMachine.toString())

    gumBallMachine.insertQuarter()
    gumBallMachine.insertQuarter()
    gumBallMachine.insertQuarter()
    gumBallMachine.insertQuarter()
    gumBallMachine.insertQuarter()
    gumBallMachine.insertQuarter()
    gumBallMachine.turnCrank()
    gumBallMachine.turnCrank()
    gumBallMachine.turnCrank()
    gumBallMachine.turnCrank()

    println(gumBallMachine.toString())

    gumBallMachine.insertQuarter()
    gumBallMachine.ejectQuarters()
    gumBallMachine.turnCrank()

    println(gumBallMachine.toString())

    gumBallMachine.insertQuarter()
    gumBallMachine.turnCrank()
    gumBallMachine.insertQuarter()
    gumBallMachine.turnCrank()
    gumBallMachine.ejectQuarters()

    println(gumBallMachine.toString())

    gumBallMachine.refill(1)

    println(gumBallMachine.toString())

    gumBallMachine.insertQuarter()
    gumBallMachine.insertQuarter()

    println(gumBallMachine.toString())

    gumBallMachine.turnCrank()
    gumBallMachine.insertQuarter()
    gumBallMachine.turnCrank()
    gumBallMachine.insertQuarter()
    gumBallMachine.turnCrank()

    println(gumBallMachine.toString())

}