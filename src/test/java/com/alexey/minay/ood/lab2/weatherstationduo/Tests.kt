package com.alexey.minay.ood.lab2.weatherstationduo

import com.alexey.minay.ood.lab2.weaterstationduo.IObserver
import com.alexey.minay.ood.lab2.weaterstationduo.WeatherDataIn
import com.alexey.minay.ood.lab2.weaterstationduo.WeatherDataOut
import com.alexey.minay.ood.lab2.weaterstationduo.WeatherInfo
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Before
import org.junit.Test

class Tests {

    private val mWeatherDataOut = WeatherDataOut()
    private val mWeatherDataIn = WeatherDataIn()
    private val mDisplay = mock<IObserver<WeatherInfo>>()

    @Before
    fun register() {
        mWeatherDataIn.register(1, mDisplay)
        mWeatherDataOut.register(1, mDisplay)
    }

    @Test
    fun shouldGetWeatherDataOutInUpdateMethodParams() {
        mWeatherDataOut.setMeasurements(1.0, 2.0, 3.0)
        verify(mDisplay).update(WeatherInfo(1.0, 2.0, 3.0), mWeatherDataOut)
    }

    @Test
    fun shouldGetWeatherDataInInUpdateMethodParams() {
        mWeatherDataIn.setMeasurements(1.0, 2.0, 3.0)
        verify(mDisplay).update(WeatherInfo(1.0, 2.0, 3.0), mWeatherDataIn)
    }

    @After
    fun remove() {
        mWeatherDataIn.remove(mDisplay)
        mWeatherDataOut.remove(mDisplay)
    }

}