package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.usecases.CalculateChangeFrameStateUseCase
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
    private val changeFrameShapeUseCase: ChangeFrameShapeUseCase,
    private val calculateChangeFrameStateUseCase: CalculateChangeFrameStateUseCase
) : MVP.ICanvasPresenter {

    private var mView: MVP.ICanvasView? = null
    private var mChangeFrameState = ChangeFrameState.NOT_RESIZE

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

    override fun onMouseMoved(x: Double, y: Double) {
        val changeFrameState = calculateChangeFrameStateUseCase(x, y)
        if (changeFrameState != mChangeFrameState) {
            mView?.updateCursor(changeFrameState)
            mChangeFrameState = changeFrameState
        }
    }

    override fun onMousePressed(x: Double, y: Double) {
        if (mChangeFrameState == ChangeFrameState.NOT_RESIZE) {
            changeSelectionUseCase(x, y)
        }
        changeFrameShapeUseCase.startMoving(x, y, mChangeFrameState)
    }

    override fun onDeleteShape() {
        TODO("Not yet implemented")
    }

    override fun onMouseReleased(x: Double, y: Double) {
        changeFrameShapeUseCase.commit()
    }

}