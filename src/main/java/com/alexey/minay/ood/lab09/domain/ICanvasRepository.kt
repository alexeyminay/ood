package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.domain.shapes.Point

interface ICanvasRepository {
    fun subscribe(onNext: (RepositoryResult) -> Unit)
    fun createNewShape(shapeType: ShapeType, parentWidth: Double, parentHeight: Double)
    fun updateCursor(mousePosition: Point)
    fun updateShapesSelection(mousePosition: Point)
    fun rememberPressedPoint(pressedPoint: Point)
    fun moveShape(newCenterPosition: Point, parentWidth: Double, parentHeight: Double)
    fun deleteSelectedShape()
    fun deleteLastPressedState()
    fun onRedo()
    fun onUndo()
}