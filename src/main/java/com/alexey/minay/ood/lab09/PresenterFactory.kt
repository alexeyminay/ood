package com.alexey.minay.ood.lab09

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.usecases.InsertShapeUseCase
import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.infrastructure.FileHelper
import com.alexey.minay.ood.lab09.ui.FxCanvasAdapter
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.ui.presenters.CanvasPresenter
import com.alexey.minay.ood.lab09.ui.presenters.FileTabPresenter
import com.alexey.minay.ood.lab09.ui.presenters.HomeTabPresenter
import com.alexey.minay.ood.lab09.ui.view.CanvasView

object PresenterFactory {

    private val mImageStateHandler by lazy { Document() }
    private val mFileHelper by lazy { FileHelper() }
    private val history = CommandHistory()
    private val mShapeSelectionModel = ShapeSelectionModel()
    private val mApplicationDocument = ApplicationDocument(mImageStateHandler, mShapeSelectionModel)

    fun createCanvasPresenterFor(view: CanvasView): MVP.ICanvasPresenter =
        CanvasPresenter(
            canvasAdapter = FxCanvasAdapter(view.graphicsContext),
            canvasAppModel = mApplicationDocument
        ).apply { onViewCreated(view) }

    fun createHomeTabPresenter(view: CanvasView): MVP.IHomeTabPresenter =
        HomeTabPresenter(
            insertShapeUseCase = InsertShapeUseCase(
                canvasAppModel = mApplicationDocument,
                history = history,
                shapeSelectionModel = mShapeSelectionModel
            )
        )

    fun createFilePresenterFor(view: MVP.IFileTabView) =
        FileTabPresenter(
        ).apply { onViewCreated(view) }

}