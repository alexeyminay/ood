package com.alexey.minay.ood.lab2.weatherstationproduo

data class WeatherInfo(
        val temperature: Double,
        val humidity: Double,
        val pressure: Double,
        val windSpeed: Double = 0.0,
        val windDirection: Int = 0
)