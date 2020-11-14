package com.alexey.minay.ood.lab09

import com.alexey.minay.ood.lab09.domain.Repository
import com.alexey.minay.ood.lab09.domain.stateHandler.ImageStateHandler
import com.alexey.minay.ood.lab09.infrastucture.FileHelper
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.ui.presenters.FileTabPresenter
import com.alexey.minay.ood.lab09.ui.presenters.MainPresenter
import com.alexey.minay.ood.lab09.ui.view.FxCanvasAdapter
import com.alexey.minay.ood.lab09.ui.view.MainView

object PresenterFactory {

    private val mImageStateHandler by lazy { ImageStateHandler() }
    private val mImageStateMemento by lazy { mImageStateHandler.ImageStateMemento() }
    private val mFileHelper by lazy { FileHelper() }
    private val mRepository by lazy { Repository(mImageStateHandler, mImageStateMemento, mFileHelper) }

    fun createCanvasPresenterFor(view: MainView): MVP.ICanvasPresenter =
            MainPresenter(
                    canvasRepository = mRepository,
                    canvasAdapter = FxCanvasAdapter(view.graphicsContext)
            ).apply { onViewCreated(view) }

    fun createFilePresenterFor(view: MVP.IFileTabView) =
            FileTabPresenter(
                    fileRepository = mRepository
            ).apply { onViewCreated(view) }
}