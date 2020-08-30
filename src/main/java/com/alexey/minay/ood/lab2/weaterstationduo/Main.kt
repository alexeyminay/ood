package com.alexey.minay.ood.lab2.weaterstationduo

fun main(args: Array<String>) {
    val weatherDataOut = WeatherDataOut()
    val weatherDataIn = WeatherDataIn()
    val display = Display()
    val statsDisplay = StatDisplay()

    weatherDataOut.register(1, display)
    weatherDataOut.register(2, statsDisplay)

    weatherDataIn.register(1, display)
    weatherDataIn.register(2, statsDisplay)

    weatherDataOut.setMeasurements(3.0, 0.7, 760.0)
    weatherDataIn.setMeasurements(23.0, 0.7, 760.0)
    weatherDataOut.setMeasurements(4.0, 0.8, 761.0)

    weatherDataOut.remove(statsDisplay)

    weatherDataOut.setMeasurements(10.0, 0.8, 761.0)
    weatherDataOut.setMeasurements(-10.0, 0.8, 761.0)
}