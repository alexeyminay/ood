package com.alexey.minay.ood.lab04.shapes

interface ICanvas {
    fun setColor(color: Color)
    fun drawLine(from: Point, to: Point)
    fun drawEllipse(center: Point, width: Int, height: Int)
}