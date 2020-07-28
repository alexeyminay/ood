package com.alexey.minay.ood.lab06.libs

interface ICanvas {
    fun moveTo(x: Int, y: Int)
    fun lineTo(x: Int, y: Int)
    fun setColor(rgbColor: Int)
}

class Canvas : ICanvas {

    override fun setColor(rgbColor: Int) {
        when {
            rgbColor > WHITE ||
                    rgbColor < BLACK -> throw RuntimeException("It isn't rgb color")
        }
        println("#${rgbColor.toString(16)}")
    }

    override fun moveTo(x: Int, y: Int) {
        println("MoveTo($x, $y)")
    }

    override fun lineTo(x: Int, y: Int) {
        println("LineTo($x, $y)")
    }

    companion object {
        private const val WHITE = 0xFFFFFF
        private const val BLACK = 0x000000
    }

}
