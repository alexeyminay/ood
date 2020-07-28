package com.alexey.minay.ood.lab06.adapters

import com.alexey.minay.ood.lab06.libs.ICanvas
import com.alexey.minay.ood.lab06.libs.MPoint
import com.alexey.minay.ood.lab06.libs.ModernGraphicsRender
import com.alexey.minay.ood.lab06.libs.RGBAColor

class ModernLibAdapter(
        private val modernGraphicsRender: ModernGraphicsRender
) : ICanvas {

    private var startPoint: MPoint? = null
    private var mColor: RGBAColor = RGBAColor.BLACK

    override fun moveTo(x: Int, y: Int) {
        modernGraphicsRender.beginDraw()
        val endPoint = MPoint(x, y)
        startPoint?.let { modernGraphicsRender.drawLine(it, endPoint, mColor) }
                ?: throw RuntimeException("Choose start point")
        modernGraphicsRender.endDrawing()
        startPoint = endPoint
    }

    override fun lineTo(x: Int, y: Int) {
        startPoint = MPoint(x, y)
    }

    override fun setColor(rgbColor: Int) {
        val rgb = rgbColor.asRGBStringColor()
        val r = rgb.substring(0..1).toIntOrNull(HEX_RADIX) ?: throw RuntimeException("Incorrect color")
        val g = rgb.substring(2..3).toIntOrNull(HEX_RADIX) ?: throw RuntimeException("Incorrect color")
        val b = rgb.substring(4..5).toIntOrNull(HEX_RADIX) ?: throw RuntimeException("Incorrect color")
        mColor = RGBAColor(r.toFloat() / MAX_HEX_VALUE, g.toFloat() / MAX_HEX_VALUE, b.toFloat() / MAX_HEX_VALUE)
    }

    private fun Int.asRGBStringColor(): String {
        val rgb = toString(HEX_RADIX)
        val stringBuilder = StringBuilder()
        if (rgb.length < RGB_SYMBOLS) {
            for (i in 0 until RGB_SYMBOLS - rgb.length) {
                stringBuilder.append(0)
            }
        }
        return stringBuilder.append(rgb).toString()
    }

    companion object {

        private const val HEX_RADIX = 16
        private const val MAX_HEX_VALUE = 255
        private const val RGB_SYMBOLS = 6

    }

}