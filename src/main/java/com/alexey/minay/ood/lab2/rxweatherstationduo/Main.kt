package com.alexey.minay.ood.lab2.rxweatherstationduo

fun main() {
    val weatherDataIn = WeatherDataIn()
    val weatherDataOut = WeatherDataOut()
    val display = Display(weatherDataIn, weatherDataOut)
    val statDisplay = StatDisplay(weatherDataIn, weatherDataOut)

    display.subscribe(ValueType.TEMPERATURE, false)
    display.subscribe(ValueType.PRESSURE, true)
    display.subscribe(ValueType.HUMIDITY, true)

    statDisplay.subscribe(ValueType.TEMPERATURE, false)
    statDisplay.subscribe(ValueType.PRESSURE, true)
    statDisplay.subscribe(ValueType.HUMIDITY, true)

    weatherDataOut.setMeasurements(3.0, 0.7, 760.0)
    weatherDataIn.setMeasurements(23.0, 0.7, 760.0)
    weatherDataOut.setMeasurements(4.0, 0.8, 761.0)

    weatherDataOut.setMeasurements(10.0, 0.8, 761.0)
    weatherDataOut.setMeasurements(-10.0, 0.8, 761.0)
}