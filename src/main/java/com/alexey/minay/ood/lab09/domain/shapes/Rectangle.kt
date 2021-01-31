package com.alexey.minay.ood.lab09.domain.shapes

import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.domain.ICanvas

class Rectangle(
        override var frame: Frame,
        override var shapeStyle: Style.Shape
) : Shape(frame) {

    private val mListX: List<Double>
        get() = listOf(frame.leftBottom.x, frame.rightBottom.x, frame.rightTop.x, frame.leftTop.x)
    private val mListY: List<Double>
        get() = listOf(frame.leftBottom.y, frame.rightBottom.y, frame.rightTop.y, frame.leftTop.y)

    override fun draw(canvasAdapter: ICanvas) {
        canvasAdapter.setStyle(shapeStyle)
        canvasAdapter.fill(mListX, mListY)
        canvasAdapter.drawLine(frame.leftTop, frame.leftBottom)
        canvasAdapter.drawLine(frame.leftBottom, frame.rightBottom)
        canvasAdapter.drawLine(frame.rightBottom, frame.rightTop)
        canvasAdapter.drawLine(frame.rightTop, frame.leftTop)
        super.draw(canvasAdapter)
    }

    companion object {

        fun createDefault(position: Point, style: Style.Shape) = Rectangle(
                frame = Frame(
                        leftTop = Point(
                                x = position.x - 50,
                                y = position.y - 50
                        ),
                        rightBottom = Point(
                                x = position.x + 50,
                                y = position.y + 50
                        )
                ),
                shapeStyle = style
        )

    }

}