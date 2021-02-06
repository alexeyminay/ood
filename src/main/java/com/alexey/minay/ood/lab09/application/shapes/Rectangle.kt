package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICanvas
import com.alexey.minay.ood.lab09.application.Style
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint

class Rectangle(
    override var frame: AppFrame,
    override val uid: Long
) : IAppShape {

    private val mListX: List<Double>
        get() = listOf(frame.leftBottom.x, frame.rightBottom.x, frame.rightTop.x, frame.leftTop.x)
    private val mListY: List<Double>
        get() = listOf(frame.leftBottom.y, frame.rightBottom.y, frame.rightTop.y, frame.leftTop.y)

    override fun draw(canvasAdapter: ICanvas) = with(canvasAdapter) {
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

}