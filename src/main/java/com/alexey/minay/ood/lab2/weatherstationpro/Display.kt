package com.alexey.minay.ood.lab2.weatherstationpro

import kotlin.math.*

class Display : IObserver<WeatherInfo> {

    override val values: MutableList<Values> = mutableListOf()

    override fun update(data: WeatherInfo) {
        values.forEach { statisticValue ->
            when (statisticValue) {
                Values.TEMP -> println("Current Temp ${data.temperature}")
                Values.HUM -> println("Current Hum ${data.humidity}")
                Values.PRESSURE -> println("Current Pressure ${data.pressure}")
                Values.WIND_SPEED -> println("Current wind speed ${data.windSpeed}")
                Values.WIND_DIRECTION -> println("Current wind direction ${data.windDirection}")

            }
        }
        println("________________________________")
    }

    enum class Values : IValues {
        TEMP, HUM, PRESSURE, WIND_SPEED, WIND_DIRECTION
    }

}

class StatDisplay : IObserver<WeatherInfo> {

    override val values: MutableList<StatisticValues> = mutableListOf()

    override fun update(data: WeatherInfo) {
        values.forEach { values ->
            when (values) {
                is StatisticValues.Temperature -> update(values.scalarValues, data.temperature, "Temperature")
                is StatisticValues.Humidity -> update(values.scalarValues, data.humidity, "humidity")
                is StatisticValues.Pressure -> update(values.scalarValues, data.pressure, "pressure")
                is StatisticValues.Wind -> updateWindData(values.vectorValues, data.windSpeed, data.windDirection)
            }
        }
    }

    private fun update(scalarValues: ScalarValues, newValue: Double, type: String) {
        if (scalarValues.minValue > newValue) {
            scalarValues.minValue = newValue
        }
        if (scalarValues.maxValue < newValue) {
            scalarValues.maxValue = newValue
        }
        scalarValues.sumValue += newValue
        ++scalarValues.measureCount

        println("Max $type ${scalarValues.maxValue}")
        println("Min $type ${scalarValues.minValue}")
        println("Average $type ${scalarValues.sumValue / scalarValues.measureCount}")
        println("________________________________")
    }

    private fun updateWindData(vectorValues: VectorValues, windSpeed: Double, windDirection: Int) {
        vectorValues.sumProjectionOnY += windSpeed * cos(Math.toRadians(windDirection.toDouble()))
        vectorValues.sumProjectionOnX += windSpeed * sin(Math.toRadians(windDirection.toDouble()))
        vectorValues.measureCount++

        val averageWindSpeed = sqrt((vectorValues.sumProjectionOnX / vectorValues.measureCount).pow(2)
                + (vectorValues.sumProjectionOnY / vectorValues.measureCount).pow(2))
        val averageWindDirection = Math.toDegrees(
                atan(vectorValues.sumProjectionOnX / vectorValues.sumProjectionOnY)
        )
        println("Average wind speed: $averageWindSpeed")
        println("Average wind direction: $averageWindDirection")
        println("________________________________")
    }

    data class ScalarValues(
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

    sealed class StatisticValues : IValues {

        class Temperature(
                val scalarValues: ScalarValues = ScalarValues()
        ) : StatisticValues()

        class Humidity(
                val scalarValues: ScalarValues = ScalarValues()
        ) : StatisticValues()

        class Pressure(
                val scalarValues: ScalarValues = ScalarValues()
        ) : StatisticValues()

        class Wind(
                val vectorValues: VectorValues = VectorValues()
        ) : StatisticValues()

    }

}