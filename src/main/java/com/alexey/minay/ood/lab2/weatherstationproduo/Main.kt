package com.alexey.minay.ood.lab2.weatherstationproduo

fun main(args: Array<String>){
    val weatherDataOut = WeatherDataOut()
    val weatherDataIn = WeatherDataIn()
    val display = Display()
    val statsDisplay = StatDisplay()
    weatherDataOut.register(1, display)
    weatherDataOut.register(2, statsDisplay)
    weatherDataIn.register(1, display)
    weatherDataIn.register(2, statsDisplay)

    weatherDataOut.setMeasurements(3.0, 0.7, 760.0, 3.0, 90)
    weatherDataIn.setMeasurements(23.0, 0.3, 745.0)
    weatherDataOut.setMeasurements(4.0, 0.8, 761.0, 3.0, 360)

    weatherDataOut.remove(statsDisplay)

    weatherDataOut.setMeasurements(10.0, 0.8, 761.0, 25.0, 12)
    weatherDataOut.setMeasurements(-10.0, 0.8, 761.0, 50.0, 14)
    weatherDataIn.setMeasurements(27.0, 0.4, 767.0)

}