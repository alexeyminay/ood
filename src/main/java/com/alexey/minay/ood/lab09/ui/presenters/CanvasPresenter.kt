package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.domain.ICanvasRepository
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.RepositoryResult
import com.alexey.minay.ood.lab09.domain.ShapeType
import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.domain.ICanvas

class CanvasPresenter(
        private val canvasRepository: ICanvasRepository,
        private val canvasAdapter: ICanvas
) : MVP.ICanvasPresenter {

    init {
        canvasRepository.subscribe(::handleRepositoryResult)
    }

    private var mView: MVP.ICanvasView? = null

    override fun onViewCreated(view: MVP.ICanvasView) {
        mView = view
    }

    override fun onDrawNewRectangle(parentWidth: Double, parentHeight: Double, style: Style.Shape) {
        canvasRepository.createNewShape(ShapeType.RECTANGLE, parentWidth, parentHeight, style)
    }

    override fun onDrawNewTriangle(parentWidth: Double, parentHeight: Double, style: Style.Shape) {
        canvasRepository.createNewShape(ShapeType.TRIANGLE, parentWidth, parentHeight, style)
    }

    override fun onDrawNewEllipse(parentWidth: Double, parentHeight: Double, style: Style.Shape) {
        canvasRepository.createNewShape(ShapeType.ELLIPSE, parentWidth, parentHeight, style)
    }

    override fun onMoveShape(newPosition: Point, parentWidth: Double, parentHeight: Double) {
        canvasRepository.moveShape(newPosition, parentWidth, parentHeight)
    }

    override fun onMouseMoved(mousePosition: Point) {
        canvasRepository.updateCursor(mousePosition)
    }

    override fun onMouseClicked(mousePosition: Point) {
        canvasRepository.updateShapesSelection(mousePosition)
    }

    override fun onMousePressed(pressedPoint: Point) {
        canvasRepository.rememberPressedPoint(pressedPoint)
    }

    override fun onDeleteShape() {
        canvasRepository.deleteSelectedShape()
    }

    override fun onMouseReleased() {
        canvasRepository.deleteLastPressedState()
    }

    override fun onStyleModified(style: Style.Shape) {
        canvasRepository.modifySelectedShapeStyle(style)
    }

    override fun onRedo() {
        canvasRepository.onRedo()
    }

    override fun onUndo() {
        canvasRepository.onUndo()
    }

    private fun handleRepositoryResult(state: RepositoryResult) {
        when (state) {
            is RepositoryResult.ImageResult -> {
                canvasAdapter.clear()
                state.state.forEach { shape ->
                    shape.draw(canvasAdapter)
                    if (shape.isSelected) mView?.updateColorPane(shape.shapeStyle)
                }
            }
            is RepositoryResult.ResizableStateResult -> {
                mView?.updateCursor(state.state)
            }
        }

    }

}