package com.alexey.minay.ood.lab09.domain.shapes

import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.ui.view.ICanvasAdapter

abstract class Shape(
        override val frame: Frame
) : IShape {

    override var isSelected: Boolean = true

    override fun draw(canvasAdapter: ICanvasAdapter) {
        if (!isSelected) {
            return
        }
        val offset = 1
        val leftOffsetTop = Point(frame.leftTop.x - offset, frame.leftTop.y - offset)
        val leftOffsetBottom = Point(frame.leftBottom.x - offset, frame.leftBottom.y + offset)
        val rightOffsetBottom = Point(frame.rightBottom.x + offset, frame.rightBottom.y + offset)
        val rightOffsetTop = Point(frame.rightTop.x + offset, frame.rightTop.y - offset)
        canvasAdapter.setStyle(Style.Frame)
        canvasAdapter.drawLine(leftOffsetTop, leftOffsetBottom)
        canvasAdapter.drawLine(leftOffsetBottom, rightOffsetBottom)
        canvasAdapter.drawLine(rightOffsetBottom, rightOffsetTop)
        canvasAdapter.drawLine(rightOffsetTop, leftOffsetTop)
        drawResizeElement(canvasAdapter, leftOffsetBottom)
        drawResizeElement(canvasAdapter, leftOffsetTop)
        drawResizeElement(canvasAdapter, rightOffsetBottom)
        drawResizeElement(canvasAdapter, rightOffsetTop)
        drawResizeElement(canvasAdapter, frame.center)
    }

    override fun isIncluding(point: Point) =
            point.x in frame.leftBottom.x..frame.rightBottom.x
                    && point.y in frame.leftTop.y..frame.leftBottom.y

    private fun drawResizeElement(canvasAdapter: ICanvasAdapter, center: Point) {
        val halfResizeElementWidth = 1
        val leftTop = Point(center.x - halfResizeElementWidth, center.y - halfResizeElementWidth)
        val rightTop = Point(center.x + halfResizeElementWidth, center.y - halfResizeElementWidth)
        val rightBottom = Point(center.x + halfResizeElementWidth, center.y + halfResizeElementWidth)
        val leftBottom = Point(center.x - halfResizeElementWidth, center.y + halfResizeElementWidth)
        canvasAdapter.drawLine(leftTop, rightTop)
        canvasAdapter.drawLine(rightTop, rightBottom)
        canvasAdapter.drawLine(rightBottom, leftBottom)
        canvasAdapter.drawLine(leftBottom, leftTop)
    }
}