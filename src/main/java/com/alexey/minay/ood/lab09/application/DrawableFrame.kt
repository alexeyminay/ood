package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint

class DrawableFrame(
    var frame: AppFrame
) : IDrawable {

    override fun draw(canvasAdapter: ICanvas) = with(canvasAdapter) {
        setStyle(Style.FRAME)
        val offset = 1
        val leftOffsetTop = AppPoint(frame.leftTop.x - offset, frame.leftTop.y - offset)
        val leftOffsetBottom = AppPoint(frame.leftBottom.x - offset, frame.leftBottom.y + offset)
        val rightOffsetBottom = AppPoint(frame.rightBottom.x + offset, frame.rightBottom.y + offset)
        val rightOffsetTop = AppPoint(frame.rightTop.x + offset, frame.rightTop.y - offset)
        drawLine(leftOffsetTop, leftOffsetBottom)
        drawLine(leftOffsetBottom, rightOffsetBottom)
        drawLine(rightOffsetBottom, rightOffsetTop)
        drawLine(rightOffsetTop, leftOffsetTop)
        drawResizeElement(canvasAdapter, leftOffsetBottom)
        drawResizeElement(canvasAdapter, leftOffsetTop)
        drawResizeElement(canvasAdapter, rightOffsetBottom)
        drawResizeElement(canvasAdapter, rightOffsetTop)
        drawResizeElement(canvasAdapter, frame.center)
    }

    private fun drawResizeElement(canvasAdapter: ICanvas, center: AppPoint) = with(canvasAdapter) {
        val resizeElementWidth = 6.0
        val leftTop = AppPoint(center.x - resizeElementWidth / 2, center.y - resizeElementWidth / 2)
        drawEllipse(leftTop, resizeElementWidth, resizeElementWidth)
        fillEllipse(leftTop, resizeElementWidth, resizeElementWidth)
    }

}