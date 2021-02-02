package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.CommandHistory
import com.alexey.minay.ood.lab09.application.ResizableState
import com.alexey.minay.ood.lab09.application.ShapeAppModel
import com.alexey.minay.ood.lab09.application.commands.ChangeFrameCommand
import com.alexey.minay.ood.lab09.domain.IShape
import com.alexey.minay.ood.lab09.domain.shapes.Frame
import com.alexey.minay.ood.lab09.domain.shapes.Point
import kotlin.math.pow

class MoveShapeUseCase(
    private val shapes: List<ShapeAppModel>,
    private val history: CommandHistory
) {

    private var mStartResizePoint: Point? = null
    private var mResizableState: ResizableState = ResizableState.NOT_RESIZE
    private var mNewFrame: Frame? = null
    private var mTargetShape: ShapeAppModel? = null

    fun startMoving(startResizePoint: Point) {
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
            history.addAnExecute(ChangeFrameCommand(mTargetShape?.shape!!, mNewFrame!!))
        }
    }

    fun moveShape(newPosition: Point, parentWidth: Double, parentHeight: Double) {
        //FIXME Доработать для перемещения нескольки фигур
        val shape = shapes.firstOrNull() ?: return
        val oldPosition = mStartResizePoint ?: return
        val differenceX = oldPosition.x - newPosition.x
        val differenceY = oldPosition.y - newPosition.y
        var newLeftTopX = shape.shape.calculateNewLeftTopX(differenceX)
        var newLeftTopY = shape.shape.calculateNewLeftTopY(differenceY)
        var newRightBottomX = shape.shape.calculateNewRightBottomX(differenceX)
        var newRightBottomY = shape.shape.calculateNewRightBottomY(differenceY)
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
        shape.frame.leftTop = Point(newLeftTopX, newLeftTopY)
        shape.frame.rightBottom = Point(newRightBottomX, newRightBottomY)
        mStartResizePoint = newPosition
    }

    private fun IShape.calculateNewLeftTopX(differenceX: Double) =
        when (mResizableState) {
            ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.x
            ResizableState.RIGHT_TOP_RESIZE -> frame.leftTop.x
            else -> frame.leftTop.x - differenceX
        }

    private fun IShape.calculateNewLeftTopY(differenceY: Double) =
        when (mResizableState) {
            ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.y
            ResizableState.LEFT_BOTTOM_RESIZE -> frame.leftTop.y
            else -> frame.leftTop.y - differenceY
        }

    private fun IShape.calculateNewRightBottomX(differenceX: Double) =
        when (mResizableState) {
            ResizableState.LEFT_BOTTOM_RESIZE -> frame.rightBottom.x
            ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.x
            else -> frame.rightBottom.x - differenceX
        }

    private fun IShape.calculateNewRightBottomY(differenceY: Double) =
        when (mResizableState) {
            ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.y
            ResizableState.RIGHT_TOP_RESIZE -> frame.rightBottom.y
            else -> frame.rightBottom.y - differenceY
        }

    private fun Point.isCross(point: Point): Boolean {
        val resizeChangePositionRadius = 3.0
        return (x - point.x).pow(2) +
                (y - point.y).pow(2) <= resizeChangePositionRadius.pow(2)
    }

}