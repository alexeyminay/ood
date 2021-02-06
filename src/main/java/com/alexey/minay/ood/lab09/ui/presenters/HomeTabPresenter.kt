package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.CommandHistoryInteractor
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.usecases.InsertShapeUseCase
import com.alexey.minay.ood.lab09.ui.MVP

class HomeTabPresenter(
    private val insertShapeUseCase: InsertShapeUseCase,
    private val historyInteractor: CommandHistoryInteractor
) : MVP.IHomeTabPresenter {

    override fun onDrawNewRectangle(parentWidth: Double, parentHeight: Double) {
        insertShapeUseCase(
            shapeType = ShapeType.RECTANGLE,
            parentWidth = parentWidth,
            parentHeight = parentHeight
        )
    }

    override fun onDrawNewTriangle(parentWidth: Double, parentHeight: Double) {
        insertShapeUseCase(
            shapeType = ShapeType.TRIANGLE,
            parentWidth = parentWidth,
            parentHeight = parentHeight
        )
    }

    override fun onDrawNewEllipse(parentWidth: Double, parentHeight: Double) {
        insertShapeUseCase(
            shapeType = ShapeType.ELLIPSE,
            parentWidth = parentWidth,
            parentHeight = parentHeight
        )
    }

    override fun onUndo() {
        historyInteractor.undo()
    }

    override fun onRedo() {
        historyInteractor.redo()
    }

}