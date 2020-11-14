package com.alexey.minay.ood.lab09.ui.view

import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.style.Style

interface ICanvasAdapter {
    fun setStyle(style: Style)
    fun drawLine(from: Point, to: Point)
    fun drawEllipse(rightTop: Point, width: Double, height: Double)
    fun fill(mListX: List<Double>, mListY: List<Double>)
    fun fillEllipse(rightTop: Point, width: Double, height: Double)
    fun clear()
}