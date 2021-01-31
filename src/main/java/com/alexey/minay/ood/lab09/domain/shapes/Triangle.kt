package com.alexey.minay.ood.lab09.domain.shapes

import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.domain.ICanvas
import kotlin.math.abs

class Triangle(
        override var frame: Frame,
        override var shapeStyle: Style.Shape
) : Shape(frame) {

    private val mVertex1: Point
        get() = Point(
                x = (frame.rightTop.x - frame.leftTop.x) / 2 + frame.leftTop.x,
                y = frame.leftTop.y
        )
    private val mVertex2: Point
        get() = frame.leftBottom
    private val mVertex3: Point
        get() = frame.rightBottom

    private val mListX: List<Double>
        get() = listOf(mVertex1.x, mVertex2.x, mVertex3.x)
    private val mListY: List<Double>
        get() = listOf(mVertex1.y, mVertex2.y, mVertex3.y)

    override fun draw(canvasAdapter: ICanvas) {
        canvasAdapter.setStyle(shapeStyle)
        canvasAdapter.fill(mListX, mListY)
        canvasAdapter.drawLine(mVertex1, mVertex2)
        canvasAdapter.drawLine(mVertex2, mVertex3)
        canvasAdapter.drawLine(mVertex3, mVertex1)
        super.draw(canvasAdapter)
    }

    override fun isIncluding(point: Point) =
            when (isSelected) {
                true -> super.isIncluding(point)
                false -> {
                    fun square(vertex1: Point, vertex2: Point, vertex3: Point) =
                            abs(vertex2.x * vertex3.y - vertex3.x * vertex2.y - vertex1.x * vertex3.y +
                                    vertex3.x * vertex1.y + vertex1.x * vertex2.y - vertex2.x * vertex1.y)

                    val sumSquareTriangles = square(point, mVertex1, mVertex2) +
                            square(point, mVertex3, mVertex1) + square(point, mVertex2, mVertex3)
                    val squareUnionTriangle = square(mVertex1, mVertex2, mVertex3)
                    abs(squareUnionTriangle - sumSquareTriangles) <= 0.01
                }
            }

    companion object {

        fun createDefault(position: Point, style: Style.Shape) = Triangle(
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