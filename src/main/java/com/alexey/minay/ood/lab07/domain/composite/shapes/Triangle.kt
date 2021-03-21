package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.IStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.Shape

class Triangle(
        style: IStyle,
        private val shapeFrame: Frame
) : Shape(style, shapeFrame) {

    override fun draw(canvas: ICanvas) {
        val lineStyle = style.lineStyle ?: return
        val fillStyle = style.fillStyle ?: return
        when {
            lineStyle.isEnable -> canvas.setLineColor(lineStyle.color)
            else -> canvas.setLineColor(RGBAColor.TRANSPARENT)
        }
        canvas.setLineWidth(lineStyle.lineWidth)
        val middleFrame = (shapeFrame.right - shapeFrame.left) / 2 + shapeFrame.left
        canvas.moveTo(middleFrame, shapeFrame.top)
        canvas.lineTo(shapeFrame.left, shapeFrame.bottom)
        canvas.lineTo(shapeFrame.right, shapeFrame.bottom)
        canvas.lineTo(middleFrame, shapeFrame.top)
        when {
            fillStyle.isEnable -> canvas.fill(fillStyle.color)
            else -> Unit
        }
    }
}