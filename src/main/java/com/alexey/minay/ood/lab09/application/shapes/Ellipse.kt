package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.domain.ICanvas
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.pow

class Ellipse(
    override var frame: AppFrame
) : DrawableFrame(frame) {

    private val leftTop: AppPoint
        get() = frame.leftTop
    private val horizontalDiameter: Double
        get() = frame.rightTop.x - frame.leftTop.x
    private val verticalDiameter: Double
        get() = frame.rightBottom.y - frame.rightTop.y

    override fun draw(canvasAdapter: ICanvas) {
        canvasAdapter.fillEllipse(leftTop, horizontalDiameter, verticalDiameter)
        canvasAdapter.drawEllipse(leftTop, horizontalDiameter, verticalDiameter)
        super.draw(canvasAdapter)
    }

    override fun isIncluding(point: AppPoint) =
        when (isSelected) {
            true -> super.isIncluding(point)
            false -> (point.x - frame.center.x).pow(2) +
                    (point.y - frame.center.y).pow(2) <= (verticalDiameter / 2).pow(2)
        }

    companion object {

        fun createDefault(position: AppPoint) = Ellipse(
            frame = AppFrame(
                leftTop = AppPoint(
                    x = position.x - 50,
                    y = position.y - 50
                ),
                rightBottom = AppPoint(
                    x = position.x + 50,
                    y = position.y + 50
                )
            )
        )

    }

}