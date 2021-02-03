package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.ICanvas
import com.alexey.minay.ood.lab09.application.Style

class Rectangle(
    override var frame: AppFrame,
) : IAppShape {

    private val mListX: List<Double>
        get() = listOf(frame.leftBottom.x, frame.rightBottom.x, frame.rightTop.x, frame.leftTop.x)
    private val mListY: List<Double>
        get() = listOf(frame.leftBottom.y, frame.rightBottom.y, frame.rightTop.y, frame.leftTop.y)

    override fun draw(canvasAdapter: ICanvas) = with(canvasAdapter){
        setStyle(Style.SHAPE)
        fill(mListX, mListY)
        drawLine(frame.leftTop, frame.leftBottom)
        drawLine(frame.leftBottom, frame.rightBottom)
        drawLine(frame.rightBottom, frame.rightTop)
        drawLine(frame.rightTop, frame.leftTop)
    }

    override fun isIncluding(point: AppPoint) =
        point.x in frame.leftBottom.x..frame.rightBottom.x
                && point.y in frame.leftTop.y..frame.leftBottom.y

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