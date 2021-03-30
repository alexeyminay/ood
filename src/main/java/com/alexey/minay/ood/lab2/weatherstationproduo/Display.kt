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

    private val mInTemperature = ScalarValues(ScalarValueType.TEMPERATURE)
    private val mOutTemperature = ScalarValues(ScalarValueType.TEMPERATURE)
    private val mInHumidity = ScalarValues(ScalarValueType.HUMIDITY)
    private val mOutHumidity = ScalarValues(ScalarValueType.HUMIDITY)
    private val mInPressure = ScalarValues(ScalarValueType.PRESSURE)
    private val mOutPressure = ScalarValues(ScalarValueType.PRESSURE)
    private val mOutWind = VectorValues()

    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>) {
        when (observable) {
            is WeatherDataOut -> {
                println("weather outside")
                update(mOutTemperature, data.temperature)
                update(mOutHumidity, data.humidity)
                update(mOutPressure, data.pressure)
                updateWindData(mOutWind, data.windSpeed, data.windDirection)
            }
            is WeatherDataIn -> {
                println("weather inside")
                update(mInTemperature, data.temperature)
                update(mInHumidity, data.humidity)
                update(mInPressure, data.pressure)
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
        println("Average $scalarValues.type.value} ${scalarValues.sumValue / scalarValues.measureCount}")
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
            atan(vectorValues.sumProjectionOnX / vectorValues.sumProjectionOnY)
        )
        println("Average wind speed: $averageWindSpeed")
        println("Average wind direction: $averageWindDirection")
        println("________________________________")
    }

    data class ScalarValues(
        val type: ScalarValueType,
        var minValue: Double = Double.POSITIVE_INFINITY,
        var maxValue: Double = Double.NEGATIVE_INFINITY,
        var sumValue: Double = 0.0,
        var measureCount: Int = 0,
        var sumProjectionOnX: Double = 0.0,
        var sumProjectionOnY: Double = 0.0,
        var sumWindSpeed: Double = 0.0
    )

    data class VectorValues(
        var sumProjectionOnX: Double = 0.0,
        var sumProjectionOnY: Double = 0.0,
        var sumVectorLength: Double = 0.0,
        var measureCount: Int = 0
    )

    enum class ScalarValueType(val value: String) {
        TEMPERATURE("temperature"),
        HUMIDITY("humidity"),
        PRESSURE("pressure")
    }

}