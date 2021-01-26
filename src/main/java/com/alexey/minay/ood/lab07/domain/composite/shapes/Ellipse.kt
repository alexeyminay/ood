package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.*
import com.alexey.minay.ood.lab07.domain.composite.FillStyle
import com.alexey.minay.ood.lab07.domain.composite.LineStyle
import com.alexey.minay.ood.lab07.domain.composite.Shape

class Ellipse(
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
        val width = frame.right - frame.left
        val height = frame.bottom - frame.top
        canvas.drawEllipse(frame.left, frame.top, width, height)
        when {
            fillStyle.isEnable -> canvas.fillEllipse(fillStyle.color, frame.left, frame.top, width, height)
            else -> Unit
        }
    }
}