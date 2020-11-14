package com.alexey.minay.ood.lab09.ui.view

import javafx.scene.canvas.GraphicsContext
import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.style.Color
import com.alexey.minay.ood.lab09.domain.style.Style

class FxCanvasAdapter(
        private val graphicsContext: GraphicsContext
) : ICanvasAdapter {

    override fun setStyle(style: Style) {
        when (style) {
            is Style.Shape -> {
                graphicsContext.lineWidth = 1.0
                graphicsContext.stroke = style.strokeColor.asFxColor()
                graphicsContext.fill = style.fillColor.asFxColor()
            }
            Style.Frame -> {
                graphicsContext.lineWidth = 1.0
                graphicsContext.stroke = Color.SKY_BLUE.asFxColor(0.4)
            }
        }
    }

    override fun drawLine(from: Point, to: Point) {
        graphicsContext.strokeLine(from.x, from.y, to.x, to.y)
        graphicsContext.strokeLine(from.x, from.y, to.x, to.y)
    }

    override fun drawEllipse(rightTop: Point, width: Double, height: Double) {
        graphicsContext.strokeOval(rightTop.x, rightTop.y, width, height)
    }

    override fun fill(mListX: List<Double>, mListY: List<Double>) {
        graphicsContext.fillPolygon(mListX.toDoubleArray(), mListY.toDoubleArray(), mListX.size)
    }

    override fun fillEllipse(rightTop: Point, width: Double, height: Double) {
        graphicsContext.fillOval(rightTop.x, rightTop.y, width, height)
    }

    override fun clear() {
        graphicsContext.clearRect(0.0, 0.0, graphicsContext.canvas.width, graphicsContext.canvas.height)
    }

}