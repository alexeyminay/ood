package com.alexey.minay.ood.lab06.libs

import java.io.BufferedWriter

data class MPoint(
        val x: Int,
        val y: Int
)

open class ModernGraphicsRender(
        private val writer: BufferedWriter
) {

    private var mDrawing = false

    fun beginDraw() {
        if (mDrawing) {
            throw RuntimeException("Drawing has already begun")
        }
        writer.appendln("<draw>")
        mDrawing = true
    }

    fun drawLine(start: MPoint, end: MPoint, color: RGBAColor) {
        if (!mDrawing) {
            throw RuntimeException("DrawLine is allowed between BeginDraw()/EndDraw() only")
        }
        writer.appendln(""" <line fromX=${start.x} fromY=${start.y} toX=${end.x} toX=${end.y}>
            |  <color r="${color.red}" g="${color.green}" b="${color.blue}" a="${color.alpha}" />
            | </line>
        """.trimMargin())
    }

    fun endDrawing() {
        if (!mDrawing) {
            throw RuntimeException("Drawing has not been started")
        }
        writer.appendln("</draw>")
        mDrawing = false
    }

}

data class RGBAColor(
        private val r: Float,
        private val g: Float,
        private val b: Float,
        private val a: Float = 1f
) {

    val red get() = getValue(r)
    val green get() = getValue(g)
    val blue get() = getValue(b)
    val alpha get() = getValue(a)

    private fun getValue(value: Float) =
            when {
                value > 1f -> 1f
                value < 0f -> 0f
                else -> value
            }

    companion object {
        val BLACK = RGBAColor(0f, 0f, 0f)
    }

}