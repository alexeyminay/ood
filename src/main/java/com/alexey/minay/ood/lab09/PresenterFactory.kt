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
    private val mInsertShapeUseCase by lazy {
        InsertShapeUseCase(
            document = mDocumentAdapter,
            history = history,
            shapeSelectionModel = mShapeSelectionModel
        )
    }

    fun createCanvasPresenterFor(
        view: MVP.ICanvasView,
        graphicsContext: GraphicsContext
    ): MVP.ICanvasPresenter {
        val mChangeSelectionUseCase by lazy { ChangeSelectionUseCase(mApplicationDocument, mShapeSelectionModel) }
        val canvasAdapter by lazy { FxCanvasAdapter(graphicsContext) }
        val calculateChangeFrameStateUseCase by lazy { CalculateChangeFrameStateUseCase(mShapeSelectionModel) }
        val deleteShapeUseCase by lazy {
            DeleteShapeUseCase(
                mDocumentAdapter,
                history,
                mShapeSelectionModel,
                mApplicationDocument
            )
        }
        return CanvasPresenter(
            canvasAdapter = canvasAdapter,
            document = mApplicationDocument,
            changeSelectionUseCase = mChangeSelectionUseCase,
            changeFrameShapeUseCase = ChangeFrameShapeUseCase(
                shapeSelectionModel = mShapeSelectionModel,
                history = history,
                document = mApplicationDocument,
                documentAdapter = mDocumentAdapter,
                framePositionCalculator = mFramePositionCalculator
            ),
            calculateChangeFrameStateUseCase = calculateChangeFrameStateUseCase,
            deleteShapeUseCase = deleteShapeUseCase
        ).apply { onViewCreated(view) }
    }

    fun createNewAppStateCanvasPresenterFor(
        view: MVP.ICanvasView,
        graphicsContext: GraphicsContext
    ): MVP.ICanvasPresenter {
        val shapeSelectionModel by lazy { ShapeSelectionModel() }
        val applicationDocument by lazy { ApplicationDocument(mDocument, shapeSelectionModel) }
        val changeSelectionUseCase by lazy { ChangeSelectionUseCase(applicationDocument, shapeSelectionModel) }
        val canvasAdapter by lazy { FxCanvasAdapter(graphicsContext) }
        val changeFrameShapeUseCase by lazy {
            ChangeFrameShapeUseCase(
                shapeSelectionModel,
                history = history,
                document = applicationDocument,
                documentAdapter = mDocumentAdapter,
                framePositionCalculator = mFramePositionCalculator
            )
        }
        val calculateChangeFrameStateUseCase = CalculateChangeFrameStateUseCase(shapeSelectionModel)
        val deleteShapeUseCase = DeleteShapeUseCase(mDocumentAdapter, history, shapeSelectionModel, applicationDocument)
        return CanvasPresenter(
            canvasAdapter = canvasAdapter,
            document = applicationDocument,
            changeSelectionUseCase = changeSelectionUseCase,
            changeFrameShapeUseCase = changeFrameShapeUseCase,
            calculateChangeFrameStateUseCase = calculateChangeFrameStateUseCase,
            deleteShapeUseCase = deleteShapeUseCase
        ).apply { onViewCreated(view) }
    }

    fun createHomeTabPresenter(): MVP.IHomeTabPresenter =
        HomeTabPresenter(
            insertShapeUseCase = mInsertShapeUseCase,
            historyInteractor = mHistoryInteractor
        )

    fun createFilePresenterFor(view: MVP.IFileTabView) =
        FileTabPresenter(
        ).apply { onViewCreated(view) }

}