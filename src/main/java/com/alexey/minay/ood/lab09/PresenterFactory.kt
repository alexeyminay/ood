package com.alexey.minay.ood.lab09

import com.alexey.minay.ood.lab09.application.ApplicationDocument
import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.DocumentAdapter
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.usecases.ChangeSelectionUseCase
import com.alexey.minay.ood.lab09.application.usecases.InsertShapeUseCase
import com.alexey.minay.ood.lab09.application.usecases.MoveShapeUseCase
import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.infrastructure.FileHelper
import com.alexey.minay.ood.lab09.ui.FxCanvasAdapter
import com.alexey.minay.ood.lab09.ui.MVP
import com.alexey.minay.ood.lab09.ui.presenters.CanvasPresenter
import com.alexey.minay.ood.lab09.ui.presenters.FileTabPresenter
import com.alexey.minay.ood.lab09.ui.presenters.HomeTabPresenter
import com.alexey.minay.ood.lab09.ui.view.CanvasView
import javafx.scene.canvas.GraphicsContext

object PresenterFactory {

    private val mDocument by lazy { Document() }
    private val mFileHelper by lazy { FileHelper() }
    private val history = CommandHistory()

    private val mShapeSelectionModel = ShapeSelectionModel()
    private val mApplicationDocument = ApplicationDocument(mDocument, mShapeSelectionModel)
    private val mDocumentAdapter = DocumentAdapter(mDocument)

    fun createCanvasPresenterFor(view: CanvasView): MVP.ICanvasPresenter {
        val mChangeSelectionUseCase = ChangeSelectionUseCase(mApplicationDocument, mShapeSelectionModel)
        return CanvasPresenter(
            canvasAdapter = FxCanvasAdapter(view.graphicsContext),
            document = mApplicationDocument,
            changeSelectionUseCase = mChangeSelectionUseCase,
            moveShapeUseCase = MoveShapeUseCase(mShapeSelectionModel, history, mApplicationDocument, mDocumentAdapter)
        ).apply { onViewCreated(view) }
    }

    fun createCanvasPresenterFor(view: CanvasView, graphicsContext: GraphicsContext): MVP.ICanvasPresenter {
        val shapeSelectionModel = ShapeSelectionModel()
        val applicationDocument = ApplicationDocument(mDocument, shapeSelectionModel)
        val changeSelectionUseCase = ChangeSelectionUseCase(applicationDocument, shapeSelectionModel)
        return CanvasPresenter(
            canvasAdapter = FxCanvasAdapter(graphicsContext),
            document = applicationDocument,
            changeSelectionUseCase = changeSelectionUseCase,
            moveShapeUseCase = MoveShapeUseCase(shapeSelectionModel, history, applicationDocument, mDocumentAdapter)
        ).apply { onViewCreated(view) }
    }

    fun createHomeTabPresenter(view: CanvasView): MVP.IHomeTabPresenter =
        HomeTabPresenter(
            insertShapeUseCase = InsertShapeUseCase(
                document = mDocumentAdapter,
                history = history,
                shapeSelectionModel = mShapeSelectionModel
            )
        )

    fun createFilePresenterFor(view: MVP.IFileTabView) =
        FileTabPresenter(
        ).apply { onViewCreated(view) }

}