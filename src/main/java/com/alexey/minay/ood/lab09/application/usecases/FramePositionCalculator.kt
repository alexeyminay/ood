package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.common.AppPoint

class FramePositionCalculator {

    fun calculate(
        shape: IAppShape,
        parentWidth: Double,
        parentHeight: Double,
        differenceX: Double,
        differenceY: Double,
        resizableState: ChangeFrameState
    ) {
        var newLeftTopX = shape.calculateNewLeftTopX(differenceX, resizableState)
        var newLeftTopY = shape.calculateNewLeftTopY(differenceY, resizableState)
        var newRightBottomX = shape.calculateNewRightBottomX(differenceX, resizableState)
        var newRightBottomY = shape.calculateNewRightBottomY(differenceY, resizableState)
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
    }

    private fun IAppShape.calculateNewLeftTopX(
        differenceX: Double,
        resizableState: ChangeFrameState
    ) = when (resizableState) {
        ChangeFrameState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.x
        ChangeFrameState.RIGHT_TOP_RESIZE -> frame.leftTop.x
        else -> frame.leftTop.x - differenceX
    }

    private fun IAppShape.calculateNewLeftTopY(
        differenceY: Double,
        resizableState: ChangeFrameState
    ) = when (resizableState) {
        ChangeFrameState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.y
        ChangeFrameState.LEFT_BOTTOM_RESIZE -> frame.leftTop.y
        else -> frame.leftTop.y - differenceY
    }

    private fun IAppShape.calculateNewRightBottomX(
        differenceX: Double,
        resizableState: ChangeFrameState
    ) = when (resizableState) {
        ChangeFrameState.LEFT_BOTTOM_RESIZE -> frame.rightBottom.x
        ChangeFrameState.LEFT_TOP_RESIZE -> frame.rightBottom.x
        else -> frame.rightBottom.x - differenceX
    }

    private fun IAppShape.calculateNewRightBottomY(
        differenceY: Double,
        resizableState: ChangeFrameState
    ) = when (resizableState) {
        ChangeFrameState.LEFT_TOP_RESIZE -> frame.rightBottom.y
        ChangeFrameState.RIGHT_TOP_RESIZE -> frame.rightBottom.y
        else -> frame.rightBottom.y - differenceY
    }

}