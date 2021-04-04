package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint

class FramePositionCalculator {

    fun calculate(
        shape: IAppShape,
        parentWidth: Double,
        parentHeight: Double,
        differenceX: Double,
        differenceY: Double,
        resizableState: ChangeFrameState,
        selectedShapesFrame: AppFrame?
    ) {
        selectedShapesFrame ?: return
        var newLeftTopX = shape.calculateNewLeftTopX(differenceX, resizableState)
        var newLeftTopY = shape.calculateNewLeftTopY(differenceY, resizableState)
        var newRightBottomX = shape.calculateNewRightBottomX(differenceX, resizableState)
        var newRightBottomY = shape.calculateNewRightBottomY(differenceY, resizableState)
        if (selectedShapesFrame.leftTop.x - OFFSET <= 0.0 && differenceX > 0.0) {
            newLeftTopX += differenceX
            if (resizableState == ChangeFrameState.NOT_RESIZE)
                newRightBottomX += differenceX
        }
        if (selectedShapesFrame.leftTop.y - OFFSET <= 0.0 && differenceY > 0.0) {
            newLeftTopY += differenceY
            if (resizableState == ChangeFrameState.NOT_RESIZE)
                newRightBottomY += differenceY
        }
        if (selectedShapesFrame.rightBottom.x + OFFSET >= parentWidth && differenceX < 0.0) {
            newRightBottomX += differenceX
            if (resizableState == ChangeFrameState.NOT_RESIZE)
                newLeftTopX += differenceX
        }
        if (selectedShapesFrame.rightBottom.y + OFFSET >= parentHeight && differenceY < 0.0) {
            newRightBottomY += differenceY
            if (resizableState == ChangeFrameState.NOT_RESIZE)
                newLeftTopY += differenceY
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

    companion object {
        private const val OFFSET = 1.0
    }

}