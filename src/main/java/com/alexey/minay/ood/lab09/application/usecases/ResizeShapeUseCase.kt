package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.ResizableState
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.pow

class ResizeShapeUseCase(
    private val shapes: List<IAppShape>,
    private val history: CommandHistory
) {

    private var mStartResizePoint: AppPoint? = null
    private var mResizableState: ResizableState = ResizableState.NOT_RESIZE
    private var mNewFrame: AppFrame? = null
    private var mTargetShape: IAppShape? = null

    fun startMoving(startResizePoint: AppPoint) {
        //FIXME Доработать для перемещения нескольки фигур
        val shape = shapes.firstOrNull() ?: return
        mStartResizePoint = startResizePoint
        mResizableState = when {
            startResizePoint.isCross(shape.frame.rightBottom) -> ResizableState.RIGHT_BOTTOM_RESIZE
            startResizePoint.isCross(shape.frame.leftTop) -> ResizableState.LEFT_TOP_RESIZE
            startResizePoint.isCross(shape.frame.rightTop) -> ResizableState.RIGHT_TOP_RESIZE
            startResizePoint.isCross(shape.frame.leftBottom) -> ResizableState.LEFT_BOTTOM_RESIZE
            else -> ResizableState.NOT_RESIZE
        }
    }

    fun commit() {
        if (mTargetShape != null && mNewFrame != null) {
        }
    }

    fun moveShape(newPosition: AppPoint, parentWidth: Double, parentHeight: Double) {
        //FIXME Доработать для перемещения нескольки фигур
        val shape = shapes.firstOrNull() ?: return
        val oldPosition = mStartResizePoint ?: return
        val differenceX = oldPosition.x - newPosition.x
        val differenceY = oldPosition.y - newPosition.y
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
        mStartResizePoint = newPosition
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