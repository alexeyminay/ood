package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.usecases.MoveShapeUseCase

class ShapeSelectionModel(
    private val history: CommandHistory
) {

    private val mSelectedShapes = mutableListOf<IAppShape>()

    fun getSelectedShapes(): List<IAppShape> {
        TODO()
    }

    fun setSelectedShapes(shapes: List<IAppShape>) {
        TODO()
    }

    fun createMoveShapeUseCase(): MoveShapeUseCase {
        return MoveShapeUseCase(mSelectedShapes, history)
    }

}