package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.shapes.Ellipse
import com.alexey.minay.ood.lab09.application.shapes.Rectangle
import com.alexey.minay.ood.lab09.application.shapes.Triangle
import com.alexey.minay.ood.lab09.domain.domainshapes.Frame
import com.alexey.minay.ood.lab09.domain.domainshapes.Point
import com.alexey.minay.ood.lab09.domain.domainshapes.Shape
import com.alexey.minay.ood.lab09.domain.domainshapes.ShapeType

fun Shape.asAppShape(): IAppShape =
    when (type) {
        ShapeType.ELLIPSE -> Ellipse(
            frame = frame.asAppFrame(),
            uid = uid
        )
        ShapeType.RECTANGLE -> Rectangle(
            frame = frame.asAppFrame(),
            uid = uid
        )
        ShapeType.TRIANGLE -> Triangle(
            frame = frame.asAppFrame(),
            uid = uid
        )
    }

fun Frame.asAppFrame() =
    AppFrame(
        leftTop = leftTop.asAppPoint(),
        rightBottom = rightBottom.asAppPoint()
    )

fun Point.asAppPoint() =
    AppPoint(x, y)

fun IAppShape.asDomainShape() =
    when (this) {
        is Triangle -> Shape(frame.asDomainFrame(), ShapeType.TRIANGLE, uid)
        is Rectangle -> Shape(frame.asDomainFrame(), ShapeType.RECTANGLE, uid)
        is Ellipse -> Shape(frame.asDomainFrame(), ShapeType.ELLIPSE, uid)
        else -> throw RuntimeException("Unknown type")
    }

fun AppFrame.asDomainFrame() =
    Frame(
        leftTop = leftTop.asDomainPoint(),
        rightBottom = rightBottom.asDomainPoint()
    )

fun AppPoint.asDomainPoint() = Point(x, y)