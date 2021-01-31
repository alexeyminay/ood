package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.ResizableState
import com.alexey.minay.ood.lab09.domain.shapes.Point

interface IImageStateHandler {
    val shapes: List<IShape>
    var resizableState: ResizableState
    fun createImage(shapeType: ShapeType, parentWidth: Double, parentHeight: Double)
    fun updateShapesSelection(mousePosition: Point)
    fun rememberPressedPoint(pressesPoint: Point)
    fun deleteSelectedShape()
    fun updateCursor(mousePosition: Point)
    fun deleteLastPressState()
    fun moveShape(newPosition: Point, parentWidth: Double, parentHeight: Double)
    fun reloadImage(shapes: List<IShape>)
}