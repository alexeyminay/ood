package com.alexey.minay.ood.lab09.ui

import com.alexey.minay.ood.lab09.domain.ICanvas
import com.alexey.minay.ood.lab09.domain.shapes.Point
import javafx.scene.canvas.GraphicsContext

class FxCanvasAdapter(
    private val graphicsContext: GraphicsContext
) : ICanvas {

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