package com.alexey.minay.ood.lab09.application

class ShapeSelectionModel(
    private val history: CommandHistory
) {

    private val mSelectedShapes = mutableListOf<ShapeAppModel>()

    fun getSelectedShapes(): List<ShapeAppModel> {
        TODO()
    }

    fun setSelectedShapes(shapes: List<ShapeAppModel>) {
        TODO()
    }

    fun createMoveShapeUseCase(): MoveShapeUseCase {
        return MoveShapeUseCase(mSelectedShapes, history)
    }

}