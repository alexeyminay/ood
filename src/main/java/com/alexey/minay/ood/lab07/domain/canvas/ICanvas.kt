package com.alexey.minay.ood.lab07.domain.canvas

import com.alexey.minay.ood.lab07.FxCanvas

interface ICanvas {
    fun fill(color: RGBAColor)
    fun fillEllipse(color: RGBAColor, left: Double, top: Double, width: Double, height: Double)
    fun moveTo(x: Double, y: Double)
    fun lineTo(x: Double, y: Double)
    fun setLineType(lineType: FxCanvas.LineType)
    fun drawEllipse(left: Double, top: Double, width: Double, height: Double)
    fun setLineColor(color: RGBAColor)
    fun clearRect(left: Double, top: Double, width: Double, height: Double)
}