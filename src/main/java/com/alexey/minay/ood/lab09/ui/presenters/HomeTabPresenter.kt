package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.application.usecases.InsertShapeUseCase
import com.alexey.minay.ood.lab09.ui.MVP

class HomeTabPresenter(
    private val insertShapeUseCase: InsertShapeUseCase,
) : MVP.IHomeTabPresenter {

    override fun onDrawNewRectangle(parentWidth: Double, parentHeight: Double) {
        insertShapeUseCase(
            shapeType = ShapeType.RECTANGLE,
            insertWidth = parentWidth,
            insetHeight = parentHeight
        )
    }

    override fun onDrawNewTriangle(parentWidth: Double, parentHeight: Double) {
        insertShapeUseCase(
            shapeType = ShapeType.TRIANGLE,
            insertWidth = parentWidth,
            insetHeight = parentHeight
        )
    }

    override fun onDrawNewEllipse(parentWidth: Double, parentHeight: Double) {
        insertShapeUseCase(
            shapeType = ShapeType.ELLIPSE,
            insertWidth = parentWidth,
            insetHeight = parentHeight
        )
    }

}