package com.alexey.minay.ood.lab09

import com.alexey.minay.ood.lab09.application.*
import com.alexey.minay.ood.lab09.application.usecases.*
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
    private val mHistoryInteractor = CommandHistoryInteractor(history)

    private val mShapeSelectionModel by lazy { ShapeSelectionModel() }
    private val mApplicationDocument by lazy { ApplicationDocument(mDocument, mShapeSelectionModel) }
    private val mDocumentAdapter by lazy { DocumentAdapter(mDocument) }
    private val mFramePositionCalculator by lazy { FramePositionCalculator() }

    fun createCanvasPresenterFor(view: CanvasView): MVP.ICanvasPresenter {
        val mChangeSelectionUseCase = ChangeSelectionUseCase(mApplicationDocument, mShapeSelectionModel)
        return CanvasPresenter(
            canvasAdapter = FxCanvasAdapter(view.graphicsContext),
            document = mApplicationDocument,
            changeSelectionUseCase = mChangeSelectionUseCase,
            changeFrameShapeUseCase = ChangeFrameShapeUseCase(
                shapeSelectionModel = mShapeSelectionModel,
                history = history,
                document = mApplicationDocument,
                documentAdapter = mDocumentAdapter,
                framePositionCalculator = mFramePositionCalculator
            ),
            calculateChangeFrameStateUseCase = CalculateChangeFrameStateUseCase(mShapeSelectionModel),
            deleteShapeUseCase = DeleteShapeUseCase(mDocumentAdapter, history, mShapeSelectionModel, mApplicationDocument)
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
            changeFrameShapeUseCase = ChangeFrameShapeUseCase(
                shapeSelectionModel,
                history = history,
                document = applicationDocument,
                documentAdapter = mDocumentAdapter,
                framePositionCalculator = mFramePositionCalculator
            ),
            calculateChangeFrameStateUseCase = CalculateChangeFrameStateUseCase(shapeSelectionModel),
            deleteShapeUseCase = DeleteShapeUseCase(mDocumentAdapter, history, shapeSelectionModel, applicationDocument)
        ).apply { onViewCreated(view) }
    }

    fun createHomeTabPresenter(view: CanvasView): MVP.IHomeTabPresenter =
        HomeTabPresenter(
            insertShapeUseCase = InsertShapeUseCase(
                document = mDocumentAdapter,
                history = history,
                shapeSelectionModel = mShapeSelectionModel
            ),
            historyInteractor = mHistoryInteractor
        )

    fun createFilePresenterFor(view: MVP.IFileTabView) =
        FileTabPresenter(
        ).apply { onViewCreated(view) }

}