package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.domain.ICanvas
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.abs

class Triangle(
    override var frame: AppFrame
) : DrawableFrame(frame) {

    private val mVertex1: AppPoint
        get() = AppPoint(
            x = (frame.rightTop.x - frame.leftTop.x) / 2 + frame.leftTop.x,
            y = frame.leftTop.y
        )
    private val mVertex2: AppPoint
        get() = frame.leftBottom
    private val mVertex3: AppPoint
        get() = frame.rightBottom

    private val mListX: List<Double>
        get() = listOf(mVertex1.x, mVertex2.x, mVertex3.x)
    private val mListY: List<Double>
        get() = listOf(mVertex1.y, mVertex2.y, mVertex3.y)

    override fun draw(canvasAdapter: ICanvas) {
        canvasAdapter.fill(mListX, mListY)
        canvasAdapter.drawLine(mVertex1, mVertex2)
        canvasAdapter.drawLine(mVertex2, mVertex3)
        canvasAdapter.drawLine(mVertex3, mVertex1)
        super.draw(canvasAdapter)
    }

    override fun isIncluding(point: AppPoint) =
        when (isSelected) {
            true -> super.isIncluding(point)
            false -> {
                fun square(vertex1: AppPoint, vertex2: AppPoint, vertex3: AppPoint) =
                    abs(
                        vertex2.x * vertex3.y - vertex3.x * vertex2.y - vertex1.x * vertex3.y +
                                vertex3.x * vertex1.y + vertex1.x * vertex2.y - vertex2.x * vertex1.y
                    )

                val sumSquareTriangles = square(point, mVertex1, mVertex2) +
                        square(point, mVertex3, mVertex1) + square(point, mVertex2, mVertex3)
                val squareUnionTriangle = square(mVertex1, mVertex2, mVertex3)
                abs(squareUnionTriangle - sumSquareTriangles) <= 0.01
            }
        }

    companion object {

        fun createDefault(position: AppPoint) = Triangle(
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