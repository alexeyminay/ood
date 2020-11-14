package com.alexey.minay.ood.lab09.domain.style

data class Color(
        val red: Double,
        val green: Double,
        val blue: Double
) {

    init {
        if (red !in 0.0..1.0) {
            throw ColorFormatException("Param red must be more then 0 and less then 1")
        }
        if (green !in 0.0..1.0) {
            throw ColorFormatException("Param green must be more then 0 and less then 1")
        }
        if (blue !in 0.0..1.0) {
            throw ColorFormatException("Param blue must be more then 0 and less then 1")
        }
    }

    companion object {

        val RED = Color(207.0 / 255, 0.0 / 255, 15.0 / 255)
        val SKY_BLUE = Color(0.0 / 255, 191.0 / 255, 255.0 / 255)
        val GREEN = Color(12.0 / 255, 200.0 / 255, 30.0 / 255)
        val BLACK = Color(0.0, 0.0, 0.0)

    }

}