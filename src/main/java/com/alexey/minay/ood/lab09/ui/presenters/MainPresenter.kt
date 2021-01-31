package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.domain.ICanvasRepository
import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.ScreenStateChanges
import com.alexey.minay.ood.lab09.domain.ShapeType
import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.ui.view.ICanvasAdapter

class MainPresenter(
        private val canvasRepository: ICanvasRepository,
        private val canvasAdapter: ICanvasAdapter
) : MVP.ICanvasPresenter {

    init {
        canvasRepository.subscribe(::handleScreenStateChanges)
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

    private fun handleScreenStateChanges(state: ScreenStateChanges) {
        when (state) {
            is ScreenStateChanges.ImageState -> {
                canvasAdapter.clear()
                state.imageState.forEach { shape ->
                    shape.draw(canvasAdapter)
                    if (shape.isSelected) mView?.updateColorPane(shape.shapeStyle)
                }
            }
            is ScreenStateChanges.ResizableState -> {
                mView?.updateCursor(state.resizableState)
            }
        }

    }

}