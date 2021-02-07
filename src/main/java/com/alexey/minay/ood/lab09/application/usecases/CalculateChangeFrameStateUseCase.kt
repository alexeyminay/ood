package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.DrawableFrame
import com.alexey.minay.ood.lab09.application.ShapeSelectionModel
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.pow

class CalculateChangeFrameStateUseCase(
    private val shapeSelectionModel: ShapeSelectionModel
) {

    operator fun invoke(x: Double, y: Double): ChangeFrameState {
        shapeSelectionModel.getSelections()
            ?.map(DrawableFrame::frame)
            ?.forEach { frame ->
                if (AppPoint(x, y).isCross(frame.leftTop)) return ChangeFrameState.LEFT_TOP_RESIZE
                if (AppPoint(x, y).isCross(frame.rightTop)) return ChangeFrameState.RIGHT_TOP_RESIZE
                if (AppPoint(x, y).isCross(frame.leftBottom)) return ChangeFrameState.LEFT_BOTTOM_RESIZE
                if (AppPoint(x, y).isCross(frame.rightBottom)) return ChangeFrameState.RIGHT_BOTTOM_RESIZE
            }
        return ChangeFrameState.NOT_RESIZE
    }

    private fun AppPoint.isCross(point: AppPoint): Boolean {
        val resizeChangePositionRadius = 6.0
        return (x - point.x).pow(2) +
                (y - point.y).pow(2) <= resizeChangePositionRadius.pow(2)
    }

}