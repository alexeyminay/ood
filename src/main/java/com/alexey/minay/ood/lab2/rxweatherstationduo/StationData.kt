package com.alexey.minay.ood.lab2.rxweatherstationduo

sealed class StationData {
    class In(val info: WeatherInfo): StationData()
    class Out(val info: WeatherInfo): StationData()
}