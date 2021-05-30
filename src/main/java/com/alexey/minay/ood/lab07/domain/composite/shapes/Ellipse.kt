package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.Shape

class Ellipse(
    fillStyle: IFillStyle,
    lineStyle: ILineStyle,
    private val shapeFrame: Frame
) : Shape(shapeFrame, fillStyle, lineStyle) {

    override fun draw(canvas: ICanvas) {
        when (lineStyle.isEnable) {
            true -> lineStyle.color?.let { canvas.setLineColor(it) }
            else -> canvas.setLineColor(RGBAColor.TRANSPARENT)
        }
        lineStyle.lineWidth?.let { canvas.setLineWidth(it) }
        val width = shapeFrame.right - shapeFrame.left
        val height = shapeFrame.bottom - shapeFrame.top
        canvas.drawEllipse(shapeFrame.left, shapeFrame.top, width, height)
        when (fillStyle.isEnable) {
            true -> fillStyle.color?.let {
                canvas.fillEllipse(
                    color = it,
                    left = shapeFrame.left,
                    top = shapeFrame.top,
                    width = width,
                    height = height
                )
            }
            else -> Unit
        }
    }
}