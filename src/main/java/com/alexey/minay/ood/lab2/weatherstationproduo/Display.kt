package com.alexey.minay.ood.lab2.weatherstationproduo

import kotlin.math.*

class Display : IObserver<WeatherInfo> {

    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>) {
        when (observable) {
            is WeatherDataOut -> {
                println("Weather outside")
                println("Current wind speed ${data.windSpeed}")
                println("Current wind direction ${data.windDirection}")
            }
            is WeatherDataIn -> println("Weather inside")
        }
        println("Current Temp ${data.temperature}")
        println("Current Hum ${data.humidity}")
        println("Current Pressure ${data.pressure}")
        println("________________________________")
    }

}

class StatDisplay : IObserver<WeatherInfo> {

    private val mInTemperature = ScalarValues()
    private val mOutTemperature = ScalarValues()
    private val mInHumidity = ScalarValues()
    private val mOutHumidity = ScalarValues()
    private val mInPressure = ScalarValues()
    private val mOutPressure = ScalarValues()
    private val mOutWind = VectorValues()

    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>) {
        when (observable) {
            is WeatherDataOut -> {
                println("weather outside")
                update(mOutTemperature, data.temperature)
                update(mOutHumidity, data.humidity)
                update(mOutPressure, data.pressure)
                printScalarValues(mOutHumidity, HUMIDITY)
                printScalarValues(mOutPressure, PRESSURE)
                printScalarValues(mOutTemperature, TEMPERATURE)

                updateWindData(mOutWind, data.windSpeed, data.windDirection)
                val averageWindSpeed = calculateAverageWindSpeed()
                val averageWindDirection = calculateAverageWindDirection()
                printVectorValues(averageWindSpeed, averageWindDirection)
            }
            is WeatherDataIn -> {
                println("weather inside")
                update(mInTemperature, data.temperature)
                update(mInHumidity, data.humidity)
                update(mInPressure, data.pressure)
                printScalarValues(mInHumidity, HUMIDITY)
                printScalarValues(mInPressure, PRESSURE)
                printScalarValues(mInTemperature, TEMPERATURE)
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
            (mOutWind.sumProjectionOnX / mOutWind.measureCount).pow(2)
                    + (mOutWind.sumProjectionOnY / mOutWind.measureCount).pow(2)
        )

    private fun calculateAverageWindDirection() =
        Math.toDegrees(
            atan2(mOutWind.sumProjectionOnX, mOutWind.sumProjectionOnY)
        )

    private fun printVectorValues(averageWindSpeed: Double, averageWindDirection: Double) {
        println("Average wind speed: $averageWindSpeed")
        println("Average wind direction: $averageWindDirection")
        println("________________________________")
    }

    data class ScalarValues(
        var minValue: Double = Double.POSITIVE_INFINITY,
        var maxValue: Double = Double.NEGATIVE_INFINITY,
        var sumValue: Double = 0.0,
        var measureCount: Int = 0,
    )

    data class VectorValues(
        var sumProjectionOnX: Double = 0.0,
        var sumProjectionOnY: Double = 0.0,
        var measureCount: Int = 0
    )

    companion object {
        private const val HUMIDITY = "humidity"
        private const val TEMPERATURE = "temperature"
        private const val PRESSURE = "pressure"
    }

}