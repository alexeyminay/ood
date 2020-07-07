package com.alexey.minay.ood.lab2.weatherstationpro

data class WeatherInfo(
        val temperature: Double,
        val humidity: Double,
        val pressure: Double,
        val windSpeed: Double,
        val windDirection: Int
)