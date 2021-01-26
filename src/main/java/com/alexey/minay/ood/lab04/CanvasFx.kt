package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.Color
import com.alexey.minay.ood.lab04.shapes.ICanvas
import com.alexey.minay.ood.lab04.shapes.Point
import javafx.scene.canvas.GraphicsContext

class CanvasFx(
        private val graphicsContext: GraphicsContext
) : ICanvas {

    override fun setColor(color: Color) {
        graphicsContext.stroke = color.asFxColor()
    }

    override fun drawLine(from: Point, to: Point) {
        graphicsContext.lineWidth = 3.0
        graphicsContext.strokeLine(from.x, from.y, to.x, to.y)
    }

    override fun drawEllipse(center: Point, width: Int, height: Int) {
        graphicsContext.strokeOval(center.x, center.y, width.toDouble(), height.toDouble())
    }
}

private fun Color.asFxColor() =
        when (this) {
            Color.RED -> javafx.scene.paint.Color.RED
            Color.GREEN -> javafx.scene.paint.Color.GREEN
            Color.BLACK -> javafx.scene.paint.Color.BLACK
            Color.BLUE -> javafx.scene.paint.Color.BLUE
            Color.YELLOW -> javafx.scene.paint.Color.YELLOW
            Color.PINK -> javafx.scene.paint.Color.PINK
        }
