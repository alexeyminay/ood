package com.alexey.minay.ood.lab07.domain

import com.alexey.minay.ood.lab07.domain.composite.RGBAColor

interface ICanvas {
    fun fill(color: RGBAColor)
    fun fillEllipse(color: RGBAColor, left: Double, top: Double, width: Double, height: Double)
    fun moveTo(x: Double, y: Double)
    fun lineTo(x: Double, y: Double)
    fun setLineWidth(lineWidth: Double)
    fun drawEllipse(left: Double, top: Double, width: Double, height: Double)
    fun setLineColor(color: RGBAColor)
    fun clearRect(left: Double, top: Double, width: Double, height: Double)
}