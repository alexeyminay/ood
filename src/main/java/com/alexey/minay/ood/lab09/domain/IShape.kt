package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.shapes.Frame
import com.alexey.minay.ood.lab09.domain.shapes.Point

interface IShape {
    var frame: Frame
    var isSelected: Boolean
    fun draw(canvasAdapter: ICanvas)
    fun isIncluding(point: Point): Boolean
}