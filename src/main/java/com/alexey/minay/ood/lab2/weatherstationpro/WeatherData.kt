package com.alexey.minay.ood.lab2.weatherstationpro

class WeatherData(
    private var temperature: Double = 0.0,
    private var humidity: Double = 0.0,
    private var pressure: Double = 0.0,
    private var windSpeed: Double = 0.0,
    private var windDirection: Int = 0
) : Observable<WeatherInfo, Type>() {

    fun setMeasurements(
        temperature: Double,
        humidity: Double,
        pressure: Double,
        windSpeed: Double,
        windDirection: Int
    ) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        this.windSpeed = windSpeed
        this.windDirection = windDirection
        notify(
            WeatherInfo(
                this.temperature,
                this.humidity,
                this.pressure,
                this.windSpeed,
                this.windDirection
            )
        )
    }

}