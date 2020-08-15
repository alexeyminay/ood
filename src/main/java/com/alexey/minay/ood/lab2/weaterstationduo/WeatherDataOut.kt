package com.alexey.minay.ood.lab2.weaterstationduo

class WeatherDataOut(
        private var mTemperature: Double = 0.0,
        private var mHumidity: Double = 0.0,
        private var mPressure: Double = 0.0
) : Observable<WeatherInfo>() {

    fun setMeasurements(temperature: Double, humidity: Double, pressure: Double) {
        mTemperature = temperature
        mHumidity = humidity
        mPressure = pressure
        notify(
                WeatherInfo(mTemperature, mHumidity, mPressure)
        )
    }

}