package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.style.Style

interface ICanvasRepository {
    fun subscribe(onNext: (ScreenStateChanges) -> Unit)
    fun createNewShape(shapeType: ShapeType, parentWidth: Double, parentHeight: Double, style: Style.Shape)
    fun updateCursor(mousePosition: Point)
    fun updateShapesSelection(mousePosition: Point)
    fun rememberPressedPoint(pressedPoint: Point)
    fun moveShape(newCenterPosition: Point, parentWidth: Double, parentHeight: Double)
    fun deleteSelectedShape()
    fun deleteLastPressedState()
    fun modifySelectedShapeStyle(style: Style.Shape)
    fun onRedo()
    fun onUndo()
}