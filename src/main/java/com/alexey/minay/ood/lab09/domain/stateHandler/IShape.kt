package com.alexey.minay.ood.lab09.domain.stateHandler

import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.shapes.Frame
import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.ui.view.ICanvasAdapter

interface IShape {
    val frame: Frame
    var isSelected: Boolean
    var shapeStyle: Style.Shape
    fun draw(canvasAdapter: ICanvasAdapter)
    fun isIncluding(point: Point): Boolean
}