package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.common.AppPoint

interface ICanvasRepository {
    fun subscribe(onNext: (RepositoryResult) -> Unit)
    fun createNewShape(shapeType: ShapeType, parentWidth: Double, parentHeight: Double)
    fun updateCursor(mousePosition: AppPoint)
    fun updateShapesSelection(mousePosition: AppPoint)
    fun rememberPressedPoint(pressedPoint: AppPoint)
    fun moveShape(newCenterPosition: AppPoint, parentWidth: Double, parentHeight: Double)
    fun deleteSelectedShape()
    fun deleteLastPressedState()
    fun onRedo()
    fun onUndo()
}