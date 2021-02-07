package com.alexey.minay.ood.lab09.infrastructure

import com.alexey.minay.ood.lab09.domain.shapes.Frame
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.shapes.Shape
import com.alexey.minay.ood.lab09.domain.shapes.ShapeType

private const val TRIANGLE = "triangle"
private const val RECTANGLE = "rectangle"
private const val ELLIPSE = "ellipse"

fun Shape.toJsonData(): ShapeJson {
    val type = when (type) {
        ShapeType.TRIANGLE -> TRIANGLE
        ShapeType.RECTANGLE -> RECTANGLE
        ShapeType.ELLIPSE -> ELLIPSE
    }
    return ShapeJson(
        type = type,
        frame = frame.toJsonData(),
        uid = uid
    )
}

private fun Frame.toJsonData(): FrameJson =
    FrameJson(
        PointJson(
            x = leftTop.x,
            y = leftTop.y
        ),
        PointJson(
            x = rightBottom.x,
            y = rightBottom.y
        )
    )

fun ShapesJson.toDomainData() =
    mutableListOf<Shape>().apply {
        shapes.forEach { shapeJson ->
            val shape = when (shapeJson.type) {
                RECTANGLE -> Shape(
                    frame = shapeJson.frame.toDomainData(),
                    uid = shapeJson.uid,
                    type = ShapeType.RECTANGLE
                )
                TRIANGLE -> Shape(
                    frame = shapeJson.frame.toDomainData(),
                    uid = shapeJson.uid,
                    type = ShapeType.TRIANGLE
                )
                ELLIPSE -> Shape(
                    frame = shapeJson.frame.toDomainData(),
                    uid = shapeJson.uid,
                    type = ShapeType.ELLIPSE
                )
                else -> throw RuntimeException("Incorrect file structure")
            }
            this.add(shape)
        }
    }

private fun FrameJson.toDomainData() =
    Frame(
        leftTop = leftTop.toDomainJson(),
        rightBottom = rightBottom.toDomainJson()
    )

private fun PointJson.toDomainJson() =
    Point(x, y)
