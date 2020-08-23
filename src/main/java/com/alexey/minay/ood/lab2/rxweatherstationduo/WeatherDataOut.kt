package com.alexey.minay.ood.lab2.rxweatherstationduo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class WeatherDataOut(
        private var mTemperature: Double = 0.0,
        private var mHumidity: Double = 0.0,
        private var mPressure: Double = 0.0
) : IObservable<StationData> {

    override val observable: Observable<StationData>
        get() = mBehaviorSubject

    private val mBehaviorSubject = BehaviorSubject.create<StationData>()

    fun setMeasurements(temperature: Double, humidity: Double, pressure: Double) {
        mTemperature = temperature
        mHumidity = humidity
        mPressure = pressure
        mBehaviorSubject.onNext(
                StationData.Out(WeatherInfo(mTemperature, mHumidity, mPressure))
        )
    }

}