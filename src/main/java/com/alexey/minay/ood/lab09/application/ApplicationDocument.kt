package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.shapes.Ellipse
import com.alexey.minay.ood.lab09.application.shapes.Rectangle
import com.alexey.minay.ood.lab09.application.shapes.Triangle
import com.alexey.minay.ood.lab09.domain.Document
import com.alexey.minay.ood.lab09.domain.domainshapes.Frame
import com.alexey.minay.ood.lab09.domain.domainshapes.Point
import com.alexey.minay.ood.lab09.domain.domainshapes.Shape
import com.alexey.minay.ood.lab09.domain.domainshapes.ShapeType
import io.reactivex.rxjava3.core.Observable

class ApplicationDocument(
    private val document: Document
) {

    val shapesObservable: Observable<List<IAppShape>>
        get() = document.state.map {
            it.map { shape ->
                shape.asAppShape()
            }
        }

    val shapeCount: Int
        get() = document.getShapeCount()

    fun getShape(index: Int) = document.getShape(index).asAppShape()

    fun insertShapeAt(index: Int, shape: IAppShape) {
        document.insertShapeAt(index, shape)
    }

    fun removeShapeAt(index: Int): IAppShape = document.removeShapeAt(index).asAppShape()

    private fun Shape.asAppShape() =
        when (type) {
            ShapeType.ELLIPSE -> Ellipse(
                frame = frame.asAppFrame()
            )
            ShapeType.RECTANGLE -> Rectangle(
                frame = frame.asAppFrame()
            )
            ShapeType.TRIANGLE -> Triangle(
                frame = frame.asAppFrame()
            )
        }

    private fun Frame.asAppFrame() =
        AppFrame(
            leftTop = leftTop.asAppPoint(),
            rightBottom = rightBottom.asAppPoint()
        )

    private fun Point.asAppPoint() =
        AppPoint(x, y)

    private fun IAppShape.asShape() =
        when(this) {
            is Triangle -> Shape
        }

}