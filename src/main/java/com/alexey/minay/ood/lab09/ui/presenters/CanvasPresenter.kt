package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.usecases.ChangeSelectionUseCase
import com.alexey.minay.ood.lab09.ui.FxCanvasAdapter
import com.alexey.minay.ood.lab09.ui.MVP
import io.reactivex.rxjava3.disposables.Disposable

class CanvasPresenter(
    private val document: ApplicationDocument,
    private val canvasAdapter: FxCanvasAdapter,
    private val changeSelectionUseCase: ChangeSelectionUseCase
) : MVP.ICanvasPresenter {

    private var mView: MVP.ICanvasView? = null
    //TODO добавить отписку при закрытии окна
    private var mDisposable: Disposable? = null

    override fun onViewCreated(view: MVP.ICanvasView) {
        mView = view
        mDisposable = document.shapesObservable.subscribe {
            canvasAdapter.clear()
            it.forEach { shape ->
                shape.draw(canvasAdapter)
            }
        }
    }


    override fun onMouseDragged(newPosition: AppPoint, parentWidth: Double, parentHeight: Double) {
    }

    override fun onMouseMoved(mousePosition: AppPoint) {

    }

    override fun onMouseClicked(x: Double, y: Double) {
        changeSelectionUseCase(x, y)
    }

    override fun onMousePressed(pressedPoint: AppPoint) {
    }

    override fun onDeleteShape() {
        TODO("Not yet implemented")
    }

    override fun onMouseReleased() {
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