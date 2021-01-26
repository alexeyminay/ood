package com.alexey.minay.ood.lab06.libs

interface ICanvas {
    fun lineTo(x: Int, y: Int)
    fun moveTo(x: Int, y: Int)
    fun setColor(hexColor: Int)
}

class Canvas : ICanvas {

    override fun setColor(hexColor: Int) {
        when {
            hexColor > WHITE ||
                    hexColor < BLACK -> throw RuntimeException("It isn't rgb color")
        }
        println("#${hexColor.toString(16)}")
    }

    override fun lineTo(x: Int, y: Int) {
        println("MoveTo($x, $y)")
    }

    override fun moveTo(x: Int, y: Int) {
        println("LineTo($x, $y)")
    }

    companion object {
        private const val WHITE = 0xFFFFFF
        private const val BLACK = 0x000000
    }

}
