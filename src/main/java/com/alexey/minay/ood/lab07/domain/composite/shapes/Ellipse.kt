package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.IStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.Shape

class Ellipse(
        style: IStyle,
        private val shapeFrame: Frame
) : Shape(style, shapeFrame) {

    override fun draw(canvas: ICanvas) {
        val lineStyle = style.lineStyle ?: return
        val fillStyle = style.fillStyle ?: return
        when {
            lineStyle.isEnable -> {
                canvas.setLineColor(lineStyle.color)
            }
            else -> canvas.setLineColor(RGBAColor.TRANSPARENT)
        }
        canvas.setLineWidth(lineStyle.lineWidth)
        val width = shapeFrame.right - shapeFrame.left
        val height = shapeFrame.bottom - shapeFrame.top
        canvas.drawEllipse(shapeFrame.left, shapeFrame.top, width, height)
        when {
            fillStyle.isEnable -> canvas.fillEllipse(
                    fillStyle.color,
                    shapeFrame.left,
                    shapeFrame.top,
                    width,
                    height
            )
            else -> Unit
        }
    }
}