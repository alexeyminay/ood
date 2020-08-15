package com.alexey.minay.ood.lab2.weatherstation

fun main(args: Array<String>) {
    val weatherData = WeatherData()
    val display = Display()
    val statsDisplay = StatDisplay()
    weatherData.register(1, display)
    weatherData.register(2, statsDisplay)

    weatherData.setMeasurements(3.0, 0.7, 760.0)
    weatherData.setMeasurements(4.0, 0.8, 761.0)

    weatherData.remove(statsDisplay)

    weatherData.setMeasurements(10.0, 0.8, 761.0)
    weatherData.setMeasurements(-10.0, 0.8, 761.0)
}