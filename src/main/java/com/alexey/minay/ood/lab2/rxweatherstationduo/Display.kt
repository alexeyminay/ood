package com.alexey.minay.ood.lab2.rxweatherstationduo

class Display(
    private val weatherDataIn: WeatherDataIn,
    private val weatherDataOut: WeatherDataOut
) : DisplayObservable() {

    override fun subscribeInsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataIn.temperatureObservable
                .subscribe { println("Current in Temperature $it") }
            ValueType.HUMIDITY -> weatherDataIn.humidityObservable
                .subscribe { println("Current in Hum $it") }
            ValueType.PRESSURE -> weatherDataIn.pressureObservable
                .subscribe { println("Current in Pressure $it") }
        }
    }

    override fun subscribeOutsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataOut.temperatureObservable
                .subscribe { println("Current Out Temperature $it") }
            ValueType.HUMIDITY -> weatherDataOut.humidityObservable
                .subscribe { println("Current Out Hum $it") }
            ValueType.PRESSURE -> weatherDataOut.pressureObservable
                .subscribe { println("Current Out Pressure $it") }
        }
    }

}

class StatDisplay(
    private val weatherDataIn: WeatherDataIn,
    private val weatherDataOut: WeatherDataOut
) : DisplayObservable() {

    private val mOutHumidity = StatisticValues(ValueType.HUMIDITY)
    private val mOutTemperature = StatisticValues(ValueType.TEMPERATURE)
    private val mOutPressure = StatisticValues(ValueType.PRESSURE)

    private val mInHumidity = StatisticValues(ValueType.HUMIDITY)
    private val mInTemperature = StatisticValues(ValueType.TEMPERATURE)
    private val mInPressure = StatisticValues(ValueType.PRESSURE)

    override fun subscribeInsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataIn.temperatureObservable
                .subscribe { update(mInTemperature, it) }
            ValueType.HUMIDITY -> weatherDataIn.humidityObservable
                .subscribe { update(mInHumidity, it) }
            ValueType.PRESSURE -> weatherDataIn.pressureObservable
                .subscribe { update(mInPressure, it) }
        }
    }

    override fun subscribeOutsideSensor(valueType: ValueType) {
        disposables += when (valueType) {
            ValueType.TEMPERATURE -> weatherDataOut.temperatureObservable
                .subscribe { update(mOutTemperature, it) }
            ValueType.HUMIDITY -> weatherDataOut.humidityObservable
                .subscribe { update(mOutHumidity, it) }
            ValueType.PRESSURE -> weatherDataOut.pressureObservable
                .subscribe { update(mOutPressure, it) }
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

}

enum class ValueType(val value: String) {
    TEMPERATURE("temperature"),
    HUMIDITY("humidity"),
    PRESSURE("pressure")
}