package com.alexey.minay.ood.lab2.weatherstation

import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class Tests {

    private val mWeatherData = WeatherData()
    private val mMockStatsDisplay = mock<StatDisplay>()
    private val mMockDisplay = mock<Display>()

    @Before
    fun register() {
        mWeatherData.register(1, mMockDisplay)
        mWeatherData.register(2, mMockStatsDisplay)
    }

    @After
    fun unregister() {
        mWeatherData.remove(mMockDisplay)
        mWeatherData.remove(mMockStatsDisplay)
    }

    @Test
    fun shouldConsiderPriority() {
        mWeatherData.setMeasurements(1.0, 2.0, 3.0)
        val order = Mockito.inOrder(mMockDisplay, mMockStatsDisplay)
        order.verify(mMockDisplay).update(WeatherInfo(1.0, 2.0, 3.0))
        order.verify(mMockStatsDisplay).update(WeatherInfo(1.0, 2.0, 3.0))
    }

}