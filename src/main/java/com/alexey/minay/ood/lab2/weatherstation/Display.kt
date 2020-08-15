package com.alexey.minay.ood.lab2.weatherstation

import com.alexey.minay.ood.lab2.weatherstation.StatDisplay.StatisticValues.ValueType

class Display : IObserver<WeatherInfo> {

    override fun update(data: WeatherInfo) {
        println("Current Temp ${data.temperature}")
        println("Current Hum ${data.humidity}")
        println("Current Pressure ${data.pressure}")
        println("________________________________")
    }

}

class StatDisplay : IObserver<WeatherInfo> {

    private val values = mutableListOf<StatisticValues>().also {
        it.add(StatisticValues(ValueType.TEMPERATURE))
        it.add(StatisticValues(ValueType.HUMIDITY))
        it.add(StatisticValues(ValueType.PRESSURE))
    }

    override fun update(data: WeatherInfo) {
        values.forEach { values ->
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