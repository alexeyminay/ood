package com.alexey.minay.ood.lab2.weatherstationpro

fun main() {
    val weatherData = WeatherData()
    val display = Display()
    display.values.add(Values.TEMPERATURE)
    val statsDisplay = StatDisplay()
    statsDisplay.values.add(Values.WIND)
    weatherData.register(1, display)
    weatherData.register(2, statsDisplay)

    weatherData.setMeasurements(3.0, 0.7, 760.0, 3.0, 180)
    weatherData.setMeasurements(4.0, 0.8, 761.0, 3.0, 270)

    weatherData.remove(statsDisplay)

    weatherData.setMeasurements(10.0, 0.8, 761.0, 0.0, 0)
    weatherData.setMeasurements(-10.0, 0.8, 761.0, 0.0, 0)
}