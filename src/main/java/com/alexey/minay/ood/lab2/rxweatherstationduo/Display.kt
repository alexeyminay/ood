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
                .subscribe { println("Current Out WindParams $it") }
        }
    }

}

class StatDisplay(
    private val weatherDataIn: WeatherDataIn,
    private val weatherDataOut: WeatherDataOut
) : DisplayObserver() {

    private val mOutHumidity = ScalarValues(ValueType.HUMIDITY)
    private val mOutTemperature = ScalarValues(ValueType.TEMPERATURE)
    private val mOutPressure = ScalarValues(ValueType.PRESSURE)
    private val mInHumidity = ScalarValues(ValueType.HUMIDITY)
    private val mInTemperature = ScalarValues(ValueType.TEMPERATURE)
    private val mInPressure = ScalarValues(ValueType.PRESSURE)
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
                .subscribe {
                    update(mOutTemperature, it)
                    printScalarValues(mOutTemperature)
                }
            ValueType.HUMIDITY -> weatherDataOut.humidityObservable
                .subscribe {
                    update(mOutHumidity, it)
                    printScalarValues(mOutTemperature)
                }
            ValueType.PRESSURE -> weatherDataOut.pressureObservable
                .subscribe {
                    update(mOutPressure, it)
                    printScalarValues(mOutTemperature)
                }
            ValueType.WIND -> weatherDataOut.windObservable
                .subscribe {
                    updateWindData(mOutWind, it.windSpeed, it.windDirection)
                    val averageWindSpeed = calculateAverageWindSpeed()
                    val averageWindDirection = calculateAverageWindDirection()
                    printVectorValues(averageWindSpeed, averageWindDirection)
                }
        }
    }

    private fun update(statValue: ScalarValues, newValue: Double) {
        if (statValue.minValue > newValue) {
            statValue.minValue = newValue
        }
        if (statValue.maxValue < newValue) {
            statValue.maxValue = newValue
        }
        statValue.sumValue += newValue
        ++statValue.measureCount
    }

    private fun updateWindData(vectorValues: VectorValues, windSpeed: Double, windDirection: Int) {
        vectorValues.sumProjectionOnY += windSpeed * cos(Math.toRadians(windDirection.toDouble()))
        vectorValues.sumProjectionOnX += windSpeed * sin(Math.toRadians(windDirection.toDouble()))
        vectorValues.measureCount++
    }

    private fun printScalarValues(scalarValue: ScalarValues) {
        println("Max ${scalarValue.type.value} ${scalarValue.maxValue}")
        println("Min ${scalarValue.type.value} ${scalarValue.minValue}")
        println("Average ${scalarValue.type.value} ${scalarValue.sumValue / scalarValue.measureCount}")
        println("________________________________")
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
        var type: ValueType,
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

enum class ValueType(val value: String) {
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    PRESSURE("pressure"),
    WIND("wind")
}