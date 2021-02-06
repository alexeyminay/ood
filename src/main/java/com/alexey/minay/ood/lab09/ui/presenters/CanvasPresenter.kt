package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.usecases.ChangeFrameShapeUseCase
import com.alexey.minay.ood.lab09.application.usecases.ChangeFrameState
import com.alexey.minay.ood.lab09.application.usecases.ChangeSelectionUseCase
import com.alexey.minay.ood.lab09.ui.FxCanvasAdapter
import com.alexey.minay.ood.lab09.ui.MVP
import io.reactivex.rxjava3.disposables.Disposable

class CanvasPresenter(
    private val document: ApplicationDocument,
    private val canvasAdapter: FxCanvasAdapter,
    private val changeSelectionUseCase: ChangeSelectionUseCase,
    private val changeFrameShapeUseCase: ChangeFrameShapeUseCase
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

    override fun onMouseDragged(x: Double, y: Double, parentWidth: Double, parentHeight: Double) {
        changeFrameShapeUseCase.moveShape(x, y, parentWidth, parentHeight)
    }

    override fun onMouseMoved(mousePosition: AppPoint) {

    }

    override fun onMouseClicked(x: Double, y: Double) {

    }

    override fun onMousePressed(x: Double, y: Double) {
        changeSelectionUseCase(x, y)
        changeFrameShapeUseCase.startMoving(x, y, ChangeFrameState.NOT_RESIZE)
    }

    override fun onDeleteShape() {
        TODO("Not yet implemented")
    }

    override fun onMouseReleased(x: Double, y: Double) {
        changeFrameShapeUseCase.commit()
    }

    override fun onStyleModified() {
        TODO("Not yet implemented")
    }

}