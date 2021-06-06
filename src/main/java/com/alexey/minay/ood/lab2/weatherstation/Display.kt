package com.alexey.minay.ood.lab2.weatherstation

class Display : IObserver<WeatherInfo> {

    override fun update(data: WeatherInfo) {
        println("Current Temp ${data.temperature}")
        println("Current Hum ${data.humidity}")
        println("Current Pressure ${data.pressure}")
        println("________________________________")
    }

}

class StatDisplay : IObserver<WeatherInfo> {

    private val temperature = StatisticValues()
    private val humidity = StatisticValues()
    private val pressure = StatisticValues()

    override fun update(data: WeatherInfo) {
        update(temperature, data.temperature)
        update(humidity, data.humidity)
        update(pressure, data.pressure)
        printStatisticValues(temperature, HUMIDITY)
        printStatisticValues(humidity, PRESSURE)
        printStatisticValues(pressure, TEMPERATURE)
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
    }

    private fun printStatisticValues(statValue: StatisticValues, valueName: String) {
        println("Max $valueName ${statValue.maxValue}")
        println("Min $valueName ${statValue.minValue}")
        println("Average $valueName ${statValue.sumValue / statValue.measureCount}")
        println("________________________________")
    }

    data class StatisticValues(
        var minValue: Double = Double.POSITIVE_INFINITY,
        var maxValue: Double = Double.NEGATIVE_INFINITY,
        var sumValue: Double = 0.0,
        var measureCount: Int = 0
    )

    companion object {
        private const val HUMIDITY = "humidity"
        private const val TEMPERATURE = "temperature"
        private const val PRESSURE = "pressure"
    }

}