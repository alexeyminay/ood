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

    override fun lineTo(x: Int, y: Int) {
        modernGraphicsRender.beginDraw()
        val endPoint = MPoint(x, y)
        startPoint?.let { modernGraphicsRender.drawLine(it, endPoint, mColor) }
                ?: throw RuntimeException("Choose start point")
        modernGraphicsRender.endDrawing()
        startPoint = endPoint
    }

    override fun moveTo(x: Int, y: Int) {
        startPoint = MPoint(x, y)
    }

    override fun setColor(hexColor: Int) {
        val r = (hexColor and 0XFF0000) ushr 16
        val g = (hexColor and 0XFF00) ushr 8
        val b = (hexColor and 0XFF)
        mColor = RGBAColor(r.toFloat() / MAX_HEX_VALUE, g.toFloat() / MAX_HEX_VALUE, b.toFloat() / MAX_HEX_VALUE)
    }

    companion object {

        private const val MAX_HEX_VALUE = 255

    }

}