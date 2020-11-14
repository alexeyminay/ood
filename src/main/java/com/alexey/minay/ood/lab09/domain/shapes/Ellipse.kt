package com.alexey.minay.ood.lab09.domain.shapes

import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.ui.view.ICanvasAdapter
import kotlin.math.pow

class Ellipse(
        override val frame: Frame,
        override var shapeStyle: Style.Shape
) : Shape(frame) {

    private val leftTop: Point
        get() = frame.leftTop
    private val horizontalDiameter: Double
        get() = frame.rightTop.x - frame.leftTop.x
    private val verticalDiameter: Double
        get() = frame.rightBottom.y - frame.rightTop.y

    override fun draw(canvasAdapter: ICanvasAdapter) {
        canvasAdapter.setStyle(shapeStyle)
        canvasAdapter.fillEllipse(leftTop, horizontalDiameter, verticalDiameter)
        canvasAdapter.drawEllipse(leftTop, horizontalDiameter, verticalDiameter)
        super.draw(canvasAdapter)
    }

    override fun isIncluding(point: Point) =
            when (isSelected) {
                true -> super.isIncluding(point)
                false -> (point.x - frame.center.x).pow(2) +
                        (point.y - frame.center.y).pow(2) <= (verticalDiameter / 2).pow(2)
            }

    companion object {

        fun createDefault(position: Point, style: Style.Shape) = Ellipse(
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