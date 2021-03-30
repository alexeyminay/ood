package com.alexey.minay.ood.lab2.rxweatherstationduo

fun main() {
    val weatherDataIn = WeatherDataIn()
    val weatherDataOut = WeatherDataOut()
    val display = Display(weatherDataIn, weatherDataOut)
    val statDisplay = StatDisplay(weatherDataIn, weatherDataOut)

    display.observe(ValueType.TEMPERATURE, false)
    display.observe(ValueType.PRESSURE, true)
    display.observe(ValueType.HUMIDITY, true)

    statDisplay.observe(ValueType.TEMPERATURE, false)
    statDisplay.observe(ValueType.PRESSURE, true)
    statDisplay.observe(ValueType.HUMIDITY, true)

    weatherDataOut.onNext(3.0, 0.7, 760.0, 10.0, 40)
    weatherDataIn.onNext(23.0, 0.7, 760.0)
    weatherDataOut.onNext(4.0, 0.8, 761.0, 20.0, 44)

    weatherDataOut.onNext(10.0, 0.8, 761.0, 10.0, 10)
    weatherDataOut.onNext(-10.0, 0.8, 761.0, 10.0, 40)
}