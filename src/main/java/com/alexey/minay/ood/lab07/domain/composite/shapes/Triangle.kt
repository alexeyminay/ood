package com.alexey.minay.ood.lab07.domain.composite.shapes

import com.alexey.minay.ood.lab07.domain.canvas.*


class Triangle(
        fillStyle: FillStyle,
        lineStyle: LineStyle,
        frame: Frame
) : Shape(fillStyle, lineStyle, frame) {

    override fun draw(canvas: ICanvas) {
        when {
            lineStyle.isEnable -> canvas.setLineColor(lineStyle.color)
            else -> canvas.setLineColor(RGBAColor.TRANSPARENT)
        }
        canvas.setLineType(FxCanvas.LineType.Shapes(lineStyle.lineWidth))
        val middleFrame = (frame.right - frame.left) / 2 + frame.left
        canvas.moveTo(middleFrame, frame.top)
        canvas.lineTo(frame.left, frame.bottom)
        canvas.lineTo(frame.right, frame.bottom)
        canvas.lineTo(middleFrame, frame.top)
        when {
            fillStyle.isEnable -> canvas.fill(fillStyle.color)
            else -> Unit
        }
    }
}