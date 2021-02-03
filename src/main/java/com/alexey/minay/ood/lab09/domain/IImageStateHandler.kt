package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.ResizableState
import com.alexey.minay.ood.lab09.application.common.AppPoint

interface IImageStateHandler {
    val shapes: List<IAppShape>
    var resizableState: ResizableState
    fun createImage(shapeType: ShapeType, parentWidth: Double, parentHeight: Double)
    fun updateShapesSelection(mousePosition: AppPoint)
    fun rememberPressedPoint(pressesPoint: AppPoint)
    fun deleteSelectedShape()
    fun updateCursor(mousePosition: AppPoint)
    fun deleteLastPressState()
    fun moveShape(newPosition: AppPoint, parentWidth: Double, parentHeight: Double)
    fun reloadImage(shapes: List<IAppShape>)
}