package com.alexey.minay.ood.lab2.rxweatherstationduo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class WeatherDataIn(
        private var mTemperature: Double = 0.0,
        private var mHumidity: Double = 0.0,
        private var mPressure: Double = 0.0
) {

    val temperatureObservable: Observable<Double>
        get() = mTemperatureObservable
    private val mTemperatureObservable = BehaviorSubject.create<Double>()
    val humidityObservable: Observable<Double>
        get() = mHumidityObservable
    private val mHumidityObservable = BehaviorSubject.create<Double>()
    val pressureObservable: Observable<Double>
        get() = mPressureObservable
    private val mPressureObservable = BehaviorSubject.create<Double>()

    fun setMeasurements(temperature: Double, humidity: Double, pressure: Double) {
        if (temperature != mTemperature) {
            mTemperature = temperature
            mTemperatureObservable.onNext(temperature)
        }
        if (humidity != mHumidity) {
            mHumidity = humidity
            mHumidityObservable.onNext(humidity)
        }
        if (pressure != mPressure) {
            mPressure = pressure
            mPressureObservable.onNext(pressure)
        }
    }

}