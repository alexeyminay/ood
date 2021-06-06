package com.alexey.minay.ood.lab2.weaterstationduo

class Display : IObserver<WeatherInfo> {

    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>) {
        when (observable) {
            is WeatherDataOut -> println("Weather outside")
            is WeatherDataIn -> println("Weather inside")
        }
        println("Current Temp ${data.temperature}")
        println("Current Hum ${data.humidity}")
        println("Current Pressure ${data.pressure}")
        println("________________________________")
    }
}

class StatDisplay : IObserver<WeatherInfo> {

    private val outTemperature = StatisticValues()
    private val outHumidity = StatisticValues()
    private val outPressure = StatisticValues()

    private val inTemperature = StatisticValues()
    private val inHumidity = StatisticValues()
    private val inPressure = StatisticValues()

    override fun update(data: WeatherInfo, observable: IObservable<WeatherInfo>) {
        when (observable) {
            is WeatherDataOut -> {
                println("Weather stat outside")
                update(outHumidity, data.humidity)
                update(outPressure, data.pressure)
                update(outTemperature, data.temperature)
                printStatisticValues(outHumidity, HUMIDITY)
                printStatisticValues(outPressure, PRESSURE)
                printStatisticValues(outTemperature, TEMPERATURE)
            }
            is WeatherDataIn -> {
                println("Weather stat inside")
                update(inHumidity, data.humidity)
                update(inPressure, data.pressure)
                update(inTemperature, data.temperature)
                printStatisticValues(inHumidity, HUMIDITY)
                printStatisticValues(inPressure, PRESSURE)
                printStatisticValues(inTemperature, TEMPERATURE)
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