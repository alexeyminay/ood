package com.alexey.minay.ood.lab2.rxweatherstationduo

import kotlin.math.*

class Display(
    private val weatherDataIn: WeatherDataIn,
    private val weatherDataOut: WeatherDataOut
) : DisplayObserver() {

    override fun observeInsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataIn.temperatureObservable
                .subscribe { println("Current in Temperature $it") }
            ValueType.HUMIDITY -> weatherDataIn.humidityObservable
                .subscribe { println("Current in Hum $it") }
            ValueType.PRESSURE -> weatherDataIn.pressureObservable
                .subscribe { println("Current in Pressure $it") }
            ValueType.WIND -> return
        }
    }

    override fun observeOutsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataOut.temperatureObservable
                .subscribe { println("Current Out Temperature $it") }
            ValueType.HUMIDITY -> weatherDataOut.humidityObservable
                .subscribe { println("Current Out Hum $it") }
            ValueType.PRESSURE -> weatherDataOut.pressureObservable
                .subscribe { println("Current Out Pressure $it") }
            ValueType.WIND -> weatherDataOut.windObservable
                .subscribe{ println("Current Out WindParams $it") }
        }
    }

}

class StatDisplay(
    private val weatherDataIn: WeatherDataIn,
    private val weatherDataOut: WeatherDataOut
) : DisplayObserver() {

    private val mOutHumidity = StatisticValues(ValueType.HUMIDITY)
    private val mOutTemperature = StatisticValues(ValueType.TEMPERATURE)
    private val mOutPressure = StatisticValues(ValueType.PRESSURE)
    private val mInHumidity = StatisticValues(ValueType.HUMIDITY)
    private val mInTemperature = StatisticValues(ValueType.TEMPERATURE)
    private val mInPressure = StatisticValues(ValueType.PRESSURE)
    private val mOutWind = VectorValues()

    override fun observeInsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataIn.temperatureObservable
                .subscribe { update(mInTemperature, it) }
            ValueType.HUMIDITY -> weatherDataIn.humidityObservable
                .subscribe { update(mInHumidity, it) }
            ValueType.PRESSURE -> weatherDataIn.pressureObservable
                .subscribe { update(mInPressure, it) }
            else -> return
        }
    }

    override fun observeOutsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataOut.temperatureObservable
                .subscribe { update(mOutTemperature, it) }
            ValueType.HUMIDITY -> weatherDataOut.humidityObservable
                .subscribe { update(mOutHumidity, it) }
            ValueType.PRESSURE -> weatherDataOut.pressureObservable
                .subscribe { update(mOutPressure, it) }
            ValueType.WIND -> weatherDataOut.windObservable
                .subscribe{ updateWindData(mOutWind, it.windSpeed, it.windDirection) }
        }
    }

    private fun update(statValue: StatisticValues, newValue: Double) {
        if (statValue.minValue > newValue) {
            statValue.minValue = newValue
        }
        if (statValue.maxValue < newValue) {
            statValue.maxValue = newValue
        }
        statValue.sumValue += newValue
        ++statValue.measureCount

        print(statValue)
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

    private fun print(statValue: StatisticValues) {
        println("Max ${statValue.type.value} ${statValue.maxValue}")
        println("Min ${statValue.type.value} ${statValue.minValue}")
        println("Average ${statValue.type.value} ${statValue.sumValue / statValue.measureCount}")
        println("________________________________")
    }

    data class StatisticValues(
        var type: ValueType,
        var minValue: Double = Double.POSITIVE_INFINITY,
        var maxValue: Double = Double.NEGATIVE_INFINITY,
        var sumValue: Double = 0.0,
        var measureCount: Int = 0
    )

    data class VectorValues(
        var sumProjectionOnX: Double = 0.0,
        var sumProjectionOnY: Double = 0.0,
        var sumVectorLength: Double = 0.0,
        var measureCount: Int = 0
    )

}

enum class ValueType(val value: String) {
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    PRESSURE("pressure"),
    WIND("wind")
}