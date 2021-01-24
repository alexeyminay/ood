package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.canvas.*

class Rectangle(
        fillStyle: FillStyle,
        lineStyle: LineStyle,
        frame: Frame
) : Shape(fillStyle, lineStyle, frame) {

    override fun draw(canvas: ICanvas) {
        when {
            lineStyle.isEnable -> canvas.setLineColor(lineStyle.color)
            else -> canvas.setLineColor(RGBAColor.TRANSPARENT)
        }
        canvas.setLineType(LineType.Shapes(lineStyle.lineWidth))
        canvas.moveTo(frame.left, frame.top)
        canvas.lineTo(frame.left, frame.bottom)
        canvas.lineTo(frame.right, frame.bottom)
        canvas.lineTo(frame.right, frame.top)
        canvas.lineTo(frame.left, frame.top)
        when {
            fillStyle.isEnable -> canvas.fill(fillStyle.color)
            else -> Unit
        }
    }
}