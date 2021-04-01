package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.ICanvas
import com.alexey.minay.ood.lab09.application.Style
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import kotlin.math.abs

class Triangle(
    override var frame: AppFrame,
    override val uid: Long
) : IAppShape {

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

    override fun draw(canvas: ICanvas) = with(canvas) {
        setStyle(Style.SHAPE)
        fill(mListX, mListY)
        drawLine(mVertex1, mVertex2)
        drawLine(mVertex2, mVertex3)
        drawLine(mVertex3, mVertex1)
    }

    override fun isIncluding(point: AppPoint): Boolean {
        fun square(vertex1: AppPoint, vertex2: AppPoint, vertex3: AppPoint) =
            abs(
                vertex2.x * vertex3.y - vertex3.x * vertex2.y - vertex1.x * vertex3.y +
                        vertex3.x * vertex1.y + vertex1.x * vertex2.y - vertex2.x * vertex1.y
            )

        val sumSquareTriangles = square(point, mVertex1, mVertex2) +
                square(point, mVertex3, mVertex1) + square(point, mVertex2, mVertex3)
        val squareUnionTriangle = square(mVertex1, mVertex2, mVertex3)
        return abs(squareUnionTriangle - sumSquareTriangles) <= 0.01
    }

}