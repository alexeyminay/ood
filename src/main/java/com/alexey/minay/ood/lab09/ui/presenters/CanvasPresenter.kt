package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.ui.FxCanvasAdapter
import com.alexey.minay.ood.lab09.ui.MVP

class CanvasPresenter(
    private val canvasAppModel: ApplicationDocument,
    private val canvasAdapter: FxCanvasAdapter
) : MVP.ICanvasPresenter {

    private var mView: MVP.ICanvasView? = null

    override fun onViewCreated(view: MVP.ICanvasView) {
        mView = view
        canvasAppModel.shapesObservable.subscribe {
            canvasAdapter.clear()
            it.forEach { shape ->
                shape.draw(canvasAdapter)
            }
        }
    }


    override fun onMoveShape(newPosition: AppPoint, parentWidth: Double, parentHeight: Double) {
        TODO("Not yet implemented")
    }

    override fun onMouseMoved(mousePosition: AppPoint) {

    }

    override fun onMouseClicked(mousePosition: AppPoint) {
        TODO("Not yet implemented")
    }

    override fun onMousePressed(pressedPoint: AppPoint) {
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