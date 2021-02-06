package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.*
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.pow

class MoveShapeUseCase(
    private val shapeSelectionModel: ShapeSelectionModel,
    private val history: CommandHistory,
    private val document: ApplicationDocument
) {

    private val mSelectedShapes = mutableListOf<IAppShape>()

    private var mOldPosition: AppPoint? = null
    private var mResizableState: ResizableState = ResizableState.NOT_RESIZE
    private var mNewFrame: AppFrame? = null
    private var mTargetShape: IAppShape? = null

    fun startMoving(x: Double, y: Double) {
        mSelectedShapes.addAll(document.getShapesBy(shapeSelectionModel.getSelectionShapeUids()))
        mOldPosition = AppPoint(x, y)
    }

    fun commit() {
        mSelectedShapes.clear()
        mOldPosition = null
    }

    fun moveShape(newPositionX: Double, newPositionY: Double, parentWidth: Double, parentHeight: Double) {
        val newPosition = AppPoint(newPositionX, newPositionY)
        val oldPosition = mOldPosition ?: return
        val differenceX = oldPosition.x - newPosition.x
        val differenceY = oldPosition.y - newPosition.y
        mSelectedShapes.forEach {
            updateShapePosition(
                shape = it,
                parentWidth = parentWidth,
                parentHeight = parentHeight,
                differenceX = differenceX,
                differenceY = differenceY,
                newPosition = newPosition
            )
        }
        document.onChanged()
    }

    private fun updateShapePosition(
        shape: IAppShape,
        parentWidth: Double,
        parentHeight: Double,
        differenceX: Double,
        differenceY: Double,
        newPosition: AppPoint
    ) {
        var newLeftTopX = shape.calculateNewLeftTopX(differenceX)
        var newLeftTopY = shape.calculateNewLeftTopY(differenceY)
        var newRightBottomX = shape.calculateNewRightBottomX(differenceX)
        var newRightBottomY = shape.calculateNewRightBottomY(differenceY)
        val offset = 1.0
        val shapeWidth = shape.frame.rightBottom.x - shape.frame.leftBottom.x
        val shapeHeight = shape.frame.leftBottom.y - shape.frame.leftTop.y
        if (newLeftTopX + offset > parentWidth - shapeWidth) {
            newLeftTopX = parentWidth - shapeWidth - offset
            newRightBottomX = parentWidth - offset
        }
        if (newLeftTopX - offset < 0.0) {
            newLeftTopX = 0.0 + offset
            newRightBottomX = shapeWidth + offset
        }
        if (newLeftTopY + offset > parentHeight - shapeHeight) {
            newLeftTopY = parentHeight - shapeHeight - offset
            newRightBottomY = parentHeight - offset
        }
        if (newLeftTopY < 0.0) {
            newLeftTopY = 0.0 + offset
            newRightBottomY = shapeHeight + offset
        }
        shape.frame.leftTop = AppPoint(newLeftTopX, newLeftTopY)
        shape.frame.rightBottom = AppPoint(newRightBottomX, newRightBottomY)
        mOldPosition = newPosition
    }

    private fun IAppShape.calculateNewLeftTopX(differenceX: Double) =
        when (mResizableState) {
            ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.x
            ResizableState.RIGHT_TOP_RESIZE -> frame.leftTop.x
            else -> frame.leftTop.x - differenceX
        }

    private fun IAppShape.calculateNewLeftTopY(differenceY: Double) =
        when (mResizableState) {
            ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.y
            ResizableState.LEFT_BOTTOM_RESIZE -> frame.leftTop.y
            else -> frame.leftTop.y - differenceY
        }

    private fun IAppShape.calculateNewRightBottomX(differenceX: Double) =
        when (mResizableState) {
            ResizableState.LEFT_BOTTOM_RESIZE -> frame.rightBottom.x
            ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.x
            else -> frame.rightBottom.x - differenceX
        }

    private fun IAppShape.calculateNewRightBottomY(differenceY: Double) =
        when (mResizableState) {
            ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.y
            ResizableState.RIGHT_TOP_RESIZE -> frame.rightBottom.y
            else -> frame.rightBottom.y - differenceY
        }

    private fun AppPoint.isCross(point: AppPoint): Boolean {
        val resizeChangePositionRadius = 3.0
        return (x - point.x).pow(2) +
                (y - point.y).pow(2) <= resizeChangePositionRadius.pow(2)
    }

}