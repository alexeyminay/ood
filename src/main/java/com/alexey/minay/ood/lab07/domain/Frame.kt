package com.alexey.minay.ood.lab07.domain

data class Frame(
        var left: Double,
        var right: Double,
        var top: Double,
        var bottom: Double
) {

    val height: Double
        get() = bottom - top

    val width: Double
        get() = right - left

}