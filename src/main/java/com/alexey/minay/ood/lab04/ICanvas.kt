package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.Color
import com.alexey.minay.ood.lab04.shapes.Point

interface ICanvas {
    fun setColor(color: Color)
    fun drawLine(from: Point, to: Point)
    fun drawEllipse(center: Point, width: Int, height: Int)
}