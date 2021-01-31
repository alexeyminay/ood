package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.shapes.Frame
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.style.Style

interface IShape {
    var frame: Frame
    var isSelected: Boolean
    var shapeStyle: Style.Shape
    fun draw(canvasAdapter: ICanvas)
    fun isIncluding(point: Point): Boolean
}