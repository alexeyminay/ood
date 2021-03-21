package com.alexey.minay.ood.lab07.domain.composite

data class RGBAColor(val r: Int, val g: Int, val b: Int, val a: Double) {

    companion object {

        val GREY_TRANSLUCENT = RGBAColor(0, 0, 0, 0.2)
        val TRANSPARENT = RGBAColor(0, 0, 0, 0.0)
        val RED = RGBAColor(207, 0, 15, 1.0)
        val BLUE = RGBAColor(30, 144, 255, 1.0)
        val SKY_BLUE = RGBAColor(0, 191, 255, 1.0)
        val GREEN = RGBAColor(12, 200, 30, 1.0)
        val YELLOW = RGBAColor(255, 255, 0, 1.0)
        val BROWN = RGBAColor(139, 69, 19, 1.0)
        val MAROON = RGBAColor(128, 0, 0, 1.0)

    }

}