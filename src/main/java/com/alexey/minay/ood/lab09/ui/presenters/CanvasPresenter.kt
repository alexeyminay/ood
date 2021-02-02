package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.CanvasAppModel
import com.alexey.minay.ood.lab09.application.usecases.InsertShapeUseCase
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.ui.FxCanvasAdapter

class CanvasPresenter(
    private val insertShapeUseCase: InsertShapeUseCase,
    private val canvasAppModel: CanvasAppModel,
    private val canvasAdapter: FxCanvasAdapter
) : MVP.ICanvasPresenter {

    private var mView: MVP.ICanvasView? = null

    override fun onViewCreated(view: MVP.ICanvasView) {
        mView = view
        canvasAppModel.shapesObservable.subscribe {
            canvasAdapter.clear()
            it.forEach { shape ->
                shape.shape.draw(canvasAdapter)
            }
        }
    }

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

    override fun onMoveShape(newPosition: Point, parentWidth: Double, parentHeight: Double) {
        TODO("Not yet implemented")
    }

    override fun onMouseMoved(mousePosition: Point) {

    }

    override fun onMouseClicked(mousePosition: Point) {
        TODO("Not yet implemented")
    }

    override fun onMousePressed(pressedPoint: Point) {
        TODO("Not yet implemented")
    }

    override fun onDeleteShape() {
        TODO("Not yet implemented")
    }

    override fun onMouseReleased() {
        TODO("Not yet implemented")
    }

    override fun onStyleModified() {
        TODO("Not yet implemented")
    }

    override fun onUndo() {
        TODO("Not yet implemented")
    }

    override fun onRedo() {
        TODO("Not yet implemented")
    }

}