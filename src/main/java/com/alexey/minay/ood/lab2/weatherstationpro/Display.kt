package com.alexey.minay.ood.lab2.weatherstationpro

import kotlin.math.*

class Display : IObserver<WeatherInfo, Type> {

    override fun update(data: WeatherInfo, type: Type) {
        when (type) {
            Type.TEMPERATURE -> println("Current Temp ${data.temperature}")
            Type.HUMIDITY -> println("Current Hum ${data.humidity}")
            Type.PRESSURE -> println("Current Pressure ${data.pressure}")
            Type.WIND -> println("Current wind speed ${data.windSpeed}")

        }
        println("________________________________")
    }

}

class StatDisplay : IObserver<WeatherInfo, Type> {

    private val mTemperature = ScalarValues(Type.TEMPERATURE)
    private val mHumidity = ScalarValues(Type.HUMIDITY)
    private val mPressure = ScalarValues(Type.PRESSURE)
    private val mWind = VectorValues()

    override fun update(data: WeatherInfo, type: Type) {
        when (type) {
            Type.TEMPERATURE -> {
                update(mTemperature, data.temperature)
                printScalarValues(mTemperature, type.value)
            }
            Type.HUMIDITY -> {
                update(mHumidity, data.humidity)
                printScalarValues(mHumidity, type.value)
            }
            Type.PRESSURE -> {
                update(mPressure, data.pressure)
                printScalarValues(mPressure, type.value)
            }
            Type.WIND -> {
                updateWindData(mWind, data.windSpeed, data.windDirection)
                val averageWindSpeed = calculateAverageWindSpeed()
                val averageWindDirection = calculateAverageWindDirection()
                printVectorValues(averageWindSpeed, averageWindDirection)
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
    }

    private fun printScalarValues(statValue: ScalarValues, valueName: String) {
        println("Max $valueName ${statValue.maxValue}")
        println("Min $valueName ${statValue.minValue}")
        println("Average $valueName ${statValue.sumValue / statValue.measureCount}")
        println("________________________________")
    }

    private fun updateWindData(vectorValues: VectorValues, windSpeed: Double, windDirection: Int) {
        vectorValues.sumProjectionOnY += windSpeed * cos(Math.toRadians(windDirection.toDouble()))
        vectorValues.sumProjectionOnX += windSpeed * sin(Math.toRadians(windDirection.toDouble()))
        vectorValues.measureCount++
    }

    private fun calculateAverageWindSpeed() =
        sqrt(
            (mWind.sumProjectionOnX / mWind.measureCount).pow(2)
                    + (mWind.sumProjectionOnY / mWind.measureCount).pow(2)
        )

    private fun calculateAverageWindDirection() =
        Math.toDegrees(
            atan2(mWind.sumProjectionOnX, mWind.sumProjectionOnY)
        )

    private fun printVectorValues(averageWindSpeed: Double, averageWindDirection: Double) {
        println("Average wind speed: $averageWindSpeed")
        println("Average wind direction: $averageWindDirection")
        println("________________________________")
    }

    data class ScalarValues(
        val type: Type,
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


enum class Type(val value: String) {
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    PRESSURE("pressure"),
    WIND("wind")
}