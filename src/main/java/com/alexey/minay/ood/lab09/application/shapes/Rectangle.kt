package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.domain.ICanvas
import com.alexey.minay.ood.lab09.application.common.AppPoint

class Rectangle(
    override var frame: AppFrame,
) : DrawableFrame(frame) {

    private val mListX: List<Double>
        get() = listOf(frame.leftBottom.x, frame.rightBottom.x, frame.rightTop.x, frame.leftTop.x)
    private val mListY: List<Double>
        get() = listOf(frame.leftBottom.y, frame.rightBottom.y, frame.rightTop.y, frame.leftTop.y)

    override fun draw(canvasAdapter: ICanvas) {
        canvasAdapter.fill(mListX, mListY)
        canvasAdapter.drawLine(frame.leftTop, frame.leftBottom)
        canvasAdapter.drawLine(frame.leftBottom, frame.rightBottom)
        canvasAdapter.drawLine(frame.rightBottom, frame.rightTop)
        canvasAdapter.drawLine(frame.rightTop, frame.leftTop)
        super.draw(canvasAdapter)
    }

    companion object {

        fun createDefault(position: AppPoint) = Rectangle(
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