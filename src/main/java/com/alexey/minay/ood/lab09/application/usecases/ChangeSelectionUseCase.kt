package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.common.AppPoint

class ChangeSelectionUseCase(
    private val document: ApplicationDocument,
    private val shapeSelectionModel: ShapeSelectionModel
) {

    operator fun invoke(x: Double, y: Double) {
        val selectingShape = document.getShapeContains(AppPoint(x, y))
        shapeSelectionModel.clearSelection()
        if (selectingShape != null) {
            shapeSelectionModel.setSelection(mutableListOf(selectingShape.frame))
        }
    }

}