package com.alexey.minay.ood.lab09

import com.alexey.minay.ood.lab09.application.CanvasAppModel
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.InsertShapeUseCase
import com.alexey.minay.ood.lab09.domain.repository.Repository
import com.alexey.minay.ood.lab09.domain.stateHandler.ImageStateHandler
import com.alexey.minay.ood.lab09.infrastructure.FileHelper
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.ui.presenters.CanvasPresenter2
import com.alexey.minay.ood.lab09.ui.presenters.FileTabPresenter
import com.alexey.minay.ood.lab09.ui.view.CanvasView
import com.alexey.minay.ood.lab09.ui.view.FxCanvasAdapter

object PresenterFactory {

    private val mImageStateHandler by lazy { ImageStateHandler() }
    private val mImageStateMemento by lazy { mImageStateHandler.ImageStateMemento() }
    private val mFileHelper by lazy { FileHelper() }
    private val mRepository by lazy { Repository(mImageStateHandler, mImageStateMemento, mFileHelper) }


    private val mCanvasAppModel = CanvasAppModel(mImageStateHandler)
    private val history = CommandHistory()

    fun createCanvasPresenterFor(view: CanvasView): MVP.ICanvasPresenter =
        CanvasPresenter2(
            insertShapeUseCase = InsertShapeUseCase(
                canvasAppModel = mCanvasAppModel,
                history = history
            ),
            canvasAdapter = FxCanvasAdapter(view.graphicsContext),
            canvasAppModel = mCanvasAppModel
        ).apply { onViewCreated(view) }

    fun createFilePresenterFor(view: MVP.IFileTabView) =
        FileTabPresenter(
            fileRepository = mRepository
        ).apply { onViewCreated(view) }
}