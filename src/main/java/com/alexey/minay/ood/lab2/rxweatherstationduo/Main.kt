package com.alexey.minay.ood.lab2.rxweatherstationduo

fun main() {
    val weatherDataIn = WeatherDataIn()
    val weatherDataOut = WeatherDataOut()
    val display = Display()
    val statDisplay = StatDisplay()
    weatherDataIn.observable.subscribe(display)
    weatherDataOut.observable.subscribe(display)
    weatherDataIn.observable.subscribe(statDisplay)
    weatherDataOut.observable.subscribe(statDisplay)

    weatherDataOut.setMeasurements(3.0, 0.7, 760.0)
    weatherDataIn.setMeasurements(23.0, 0.7, 760.0)
    weatherDataOut.setMeasurements(4.0, 0.8, 761.0)

    weatherDataOut.setMeasurements(10.0, 0.8, 761.0)
    weatherDataOut.setMeasurements(-10.0, 0.8, 761.0)
}