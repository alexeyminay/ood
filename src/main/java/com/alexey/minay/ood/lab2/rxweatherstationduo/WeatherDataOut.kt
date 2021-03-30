package com.alexey.minay.ood.lab2.rxweatherstationduo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class WeatherDataOut(
    private var mTemperature: Double = 0.0,
    private var mHumidity: Double = 0.0,
    private var mPressure: Double = 0.0,
    private var mWindSpeed: Double = 0.0,
    private var mWindDirection: Int = 0
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

    val windObservable: Observable<WindParams>
        get() = mWindObservable
    private val mWindObservable = BehaviorSubject.create<WindParams>()

    fun onNext(
        temperature: Double,
        humidity: Double,
        pressure: Double,
        windSpeed: Double,
        windDirection: Int
    ) {
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
        if (windDirection != mWindDirection || windSpeed != mWindSpeed) {
            mWindSpeed = windSpeed
            mWindDirection = windDirection
            mWindObservable.onNext(WindParams(windSpeed, windDirection))
        }
    }

}

data class WindParams(
    val windSpeed: Double,
    val windDirection: Int
)