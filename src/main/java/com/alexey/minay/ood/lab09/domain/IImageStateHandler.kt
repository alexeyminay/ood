package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.ResizableState
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.style.Style

interface IImageStateHandler {
    val shapes: List<IShape>
    var resizableState: ResizableState
    fun createImage(shapeType: ShapeType, parentWidth: Double, parentHeight: Double, style: Style.Shape)
    fun updateShapesSelection(mousePosition: Point)
    fun rememberPressedPoint(pressesPoint: Point)
    fun deleteSelectedShape()
    fun updateCursor(mousePosition: Point)
    fun deleteLastPressState()
    fun modifySelectedShapeStyle(style: Style.Shape)
    fun moveShape(newPosition: Point, parentWidth: Double, parentHeight: Double)
    fun reloadImage(shapes: List<IShape>)
}