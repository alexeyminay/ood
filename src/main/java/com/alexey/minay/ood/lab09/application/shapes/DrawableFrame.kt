package com.alexey.minay.ood.lab09.application.shapes

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.IDrawable
import com.alexey.minay.ood.lab09.domain.ICanvas
import com.alexey.minay.ood.lab09.application.common.AppPoint

abstract class DrawableFrame(
        var frame: AppFrame
) : IDrawable {

    override fun draw(canvasAdapter: ICanvas) {
        val offset = 1
        val leftOffsetTop = AppPoint(frame.leftTop.x - offset, frame.leftTop.y - offset)
        val leftOffsetBottom = AppPoint(frame.leftBottom.x - offset, frame.leftBottom.y + offset)
        val rightOffsetBottom = AppPoint(frame.rightBottom.x + offset, frame.rightBottom.y + offset)
        val rightOffsetTop = AppPoint(frame.rightTop.x + offset, frame.rightTop.y - offset)
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

    private fun drawResizeElement(canvasAdapter: ICanvas, center: AppPoint) {
        val halfResizeElementWidth = 1
        val leftTop = AppPoint(center.x - halfResizeElementWidth, center.y - halfResizeElementWidth)
        val rightTop = AppPoint(center.x + halfResizeElementWidth, center.y - halfResizeElementWidth)
        val rightBottom = AppPoint(center.x + halfResizeElementWidth, center.y + halfResizeElementWidth)
        val leftBottom = AppPoint(center.x - halfResizeElementWidth, center.y + halfResizeElementWidth)
        canvasAdapter.drawLine(leftTop, rightTop)
        canvasAdapter.drawLine(rightTop, rightBottom)
        canvasAdapter.drawLine(rightBottom, leftBottom)
        canvasAdapter.drawLine(leftBottom, leftTop)
    }

}