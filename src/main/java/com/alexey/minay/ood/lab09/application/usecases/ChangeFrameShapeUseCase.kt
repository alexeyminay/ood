package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.*
import com.alexey.minay.ood.lab09.application.commands.ChangeShapeFrameCommand
import com.alexey.minay.ood.lab09.application.common.AppPoint

class ChangeFrameShapeUseCase(
    private val shapeSelectionModel: ShapeSelectionModel,
    private val history: CommandHistory,
    private val document: ApplicationDocument,
    private val documentAdapter: DocumentAdapter,
    private val framePositionCalculator: FramePositionCalculator
) {

    private val mSelectedShapes = mutableListOf<IAppShape>()
    private var mOldPosition: AppPoint? = null
    private var isDragget = false
    private var mChangeFrameState: ChangeFrameState = ChangeFrameState.NOT_RESIZE

    fun startMoving(x: Double, y: Double, changeFrameState: ChangeFrameState) {
        if (mOldPosition != null) return
        mSelectedShapes.addAll(document.getShapesBy(shapeSelectionModel.getSelectionShapeUids()))
        mOldPosition = AppPoint(x, y)
        mChangeFrameState = changeFrameState
    }

    fun commit() {
        if (isDragget) {
            val selections = mutableListOf<IAppShape>().apply { addAll(mSelectedShapes) }
            val changeShapeFrameCommand = ChangeShapeFrameCommand(
                documentAdapter = documentAdapter,
                changedShapes = selections,
                selectionModel = shapeSelectionModel
            )
            history.addAnExecute(changeShapeFrameCommand)
        }
        mSelectedShapes.clear()
        mOldPosition = null
        isDragget = false
    }

    fun moveShape(newPositionX: Double, newPositionY: Double, parentWidth: Double, parentHeight: Double) {
        if (newPositionX == mOldPosition?.x && newPositionY == mOldPosition?.y) {
            return
        }
        isDragget = true
        if (mChangeFrameState == ChangeFrameState.NOT_RESIZE)
            shapeSelectionModel.clearSelection()
        val newPosition = AppPoint(newPositionX, newPositionY)
        val oldPosition = mOldPosition ?: return
        val differenceX = oldPosition.x - newPosition.x
        val differenceY = oldPosition.y - newPosition.y
        mSelectedShapes.forEach {
            framePositionCalculator.calculate(
                shape = it,
                parentWidth = parentWidth,
                parentHeight = parentHeight,
                differenceX = differenceX,
                differenceY = differenceY,
                resizableState = mChangeFrameState
            )
            mOldPosition = newPosition
        }
        document.onChanged()
    }

}