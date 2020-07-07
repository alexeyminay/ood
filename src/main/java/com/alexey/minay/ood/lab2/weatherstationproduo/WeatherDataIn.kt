package com.alexey.minay.ood.lab2.weatherstationproduo

class WeatherDataIn(
        private var temperature: Double = 0.0,
        private var humidity: Double = 0.0,
        private var pressure: Double = 0.0
) : Observable<WeatherInfo>() {

    fun setMeasurements(temperature: Double, humidity: Double, pressure: Double) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        notify(
                WeatherInfo(
                        this.temperature,
                        this.humidity,
                        this.pressure
                )
        )
    }

}