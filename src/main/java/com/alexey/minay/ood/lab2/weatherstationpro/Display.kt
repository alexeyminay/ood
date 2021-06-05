package com.alexey.minay.ood.lab2.weatherstationpro

import kotlin.math.*

class Display : IObserver<WeatherInfo> {

    override val values: MutableList<Values> = mutableListOf()

    override fun update(data: WeatherInfo) {
        values.forEach { statisticValue ->
            when (statisticValue) {
                Values.TEMPERATURE -> println("Current Temp ${data.temperature}")
                Values.HUMIDITY -> println("Current Hum ${data.humidity}")
                Values.PRESSURE -> println("Current Pressure ${data.pressure}")
                Values.WIND -> println("Current wind speed ${data.windSpeed}")

            }
        }
        println("________________________________")
    }

}

class StatDisplay : IObserver<WeatherInfo> {

    private val mTemperature = ScalarValues(Values.TEMPERATURE)
    private val mHumidity = ScalarValues(Values.HUMIDITY)
    private val mPressure = ScalarValues(Values.PRESSURE)
    private val mWind = VectorValues()

    override val values: MutableList<Values> = mutableListOf()

    override fun update(data: WeatherInfo) {
        values.forEach { values ->
            when (values) {
                Values.TEMPERATURE -> update(mTemperature, data.temperature)
                Values.HUMIDITY -> update(mHumidity, data.humidity)
                Values.PRESSURE -> update(mPressure, data.pressure)
                Values.WIND -> updateWindData(mWind, data.windSpeed, data.windDirection)
            }
        }
    }

    private fun update(scalarValues: ScalarValues, newValue: Double) {
        if (scalarValues.minValue > newValue) {
            scalarValues.minValue = newValue
        }
        if (scalarValues.maxValue < newValue) {
            scalarValues.maxValue = newValue
        }
        scalarValues.sumValue += newValue
        ++scalarValues.measureCount

        println("Max ${scalarValues.type.value} ${scalarValues.maxValue}")
        println("Min ${scalarValues.type.value} ${scalarValues.minValue}")
        println("Average ${scalarValues.type.value} ${scalarValues.sumValue / scalarValues.measureCount}")
        println("________________________________")
    }

    private fun updateWindData(vectorValues: VectorValues, windSpeed: Double, windDirection: Int) {
        vectorValues.sumProjectionOnY += windSpeed * cos(Math.toRadians(windDirection.toDouble()))
        vectorValues.sumProjectionOnX += windSpeed * sin(Math.toRadians(windDirection.toDouble()))
        vectorValues.measureCount++

        val averageWindSpeed = sqrt(
            (vectorValues.sumProjectionOnX / vectorValues.measureCount).pow(2)
                    + (vectorValues.sumProjectionOnY / vectorValues.measureCount).pow(2)
        )
        val averageWindDirection = Math.toDegrees(
            atan2(vectorValues.sumProjectionOnX, vectorValues.sumProjectionOnY)
        )
        println("Average wind speed: $averageWindSpeed")
        println("Average wind direction: $averageWindDirection")
        println("________________________________")
    }

    data class ScalarValues(
        val type: Values,
        var minValue: Double = Double.POSITIVE_INFINITY,
        var maxValue: Double = Double.NEGATIVE_INFINITY,
        var sumValue: Double = 0.0,
        var measureCount: Int = 0
    )

    data class VectorValues(
        var sumProjectionOnX: Double = 0.0,
        var sumProjectionOnY: Double = 0.0,
        var measureCount: Int = 0
    )

}