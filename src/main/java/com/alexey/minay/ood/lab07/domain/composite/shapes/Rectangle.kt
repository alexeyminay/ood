package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.Shape

class Rectangle(
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
        canvas.moveTo(shapeFrame.left, shapeFrame.top)
        canvas.lineTo(shapeFrame.left, shapeFrame.bottom)
        canvas.lineTo(shapeFrame.right, shapeFrame.bottom)
        canvas.lineTo(shapeFrame.right, shapeFrame.top)
        canvas.lineTo(shapeFrame.left, shapeFrame.top)
        when (fillStyle.isEnable) {
            true -> fillStyle.color?.let { canvas.fill(it) }
            else -> Unit
        }
    }
}