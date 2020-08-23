package com.alexey.minay.ood.lab2.rxweatherstationduo

import com.alexey.minay.ood.lab2.rxweatherstationduo.StatDisplay.StatisticValues.ValueType

class Display : Observer<StationData>() {

    override fun onNext(stationData: StationData?) {
        when (stationData) {
            is StationData.Out -> {
                println("Weather outside")
                update(stationData.info)
            }
            is StationData.In -> {
                println("Weather inside")
                update(stationData.info)
            }
        }
    }

    fun update(data: WeatherInfo) {
        println("Current Temp ${data.temperature}")
        println("Current Hum ${data.humidity}")
        println("Current Pressure ${data.pressure}")
        println("________________________________")
    }

}

class StatDisplay : Observer<StationData>() {

    private val valuesOut = mutableListOf<StatisticValues>().also {
        it.add(StatisticValues(ValueType.TEMPERATURE))
        it.add(StatisticValues(ValueType.HUMIDITY))
        it.add(StatisticValues(ValueType.PRESSURE))
    }

    private val valuesIn = mutableListOf<StatisticValues>().also {
        it.add(StatisticValues(ValueType.TEMPERATURE))
        it.add(StatisticValues(ValueType.HUMIDITY))
        it.add(StatisticValues(ValueType.PRESSURE))
    }

    override fun onNext(stationData: StationData?) {
        when (stationData) {
            is StationData.Out -> {
                println("Weather stat outside")
                update(valuesOut, stationData.info)
            }
            is StationData.In -> {
                println("Weather stat inside")
                update(valuesIn, stationData.info)
            }
        }
    }

    private fun update(updatingValues: List<StatisticValues>, data: WeatherInfo) {
        updatingValues.forEach { values ->
            when (values.valueType) {
                ValueType.TEMPERATURE -> update(values, data.temperature)
                ValueType.HUMIDITY -> update(values, data.humidity)
                ValueType.PRESSURE -> update(values, data.pressure)
            }
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

        println("Max ${statValue.valueType} ${statValue.maxValue}")
        println("Min ${statValue.valueType} ${statValue.minValue}")
        println("Average ${statValue.valueType} ${statValue.sumValue / statValue.measureCount}")
        println("________________________________")
    }

    data class StatisticValues(
            val valueType: ValueType
    ) {
        var minValue: Double = Double.POSITIVE_INFINITY
        var maxValue: Double = Double.NEGATIVE_INFINITY
        var sumValue: Double = 0.0
        var measureCount: Int = 0

        enum class ValueType {
            TEMPERATURE,
            HUMIDITY,
            PRESSURE
        }
    }

}