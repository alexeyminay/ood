package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.style.Style

interface ICanvas {
    fun setStyle(style: Style)
    fun drawLine(from: Point, to: Point)
    fun drawEllipse(rightTop: Point, width: Double, height: Double)
    fun fill(mListX: List<Double>, mListY: List<Double>)
    fun fillEllipse(rightTop: Point, width: Double, height: Double)
    fun clear()
}