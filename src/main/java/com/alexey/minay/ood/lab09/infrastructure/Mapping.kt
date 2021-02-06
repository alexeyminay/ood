package com.alexey.minay.ood.lab09.infrastructure

import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.shapes.Ellipse
import com.alexey.minay.ood.lab09.application.shapes.Rectangle
import com.alexey.minay.ood.lab09.application.shapes.Triangle

private const val TRIANGLE = "triangle"
private const val RECTANGLE = "rectangle"
private const val ELLIPSE = "ellipse"

fun IAppShape.toJsonData(): ShapeJson {
    val type = when (this) {
        is Triangle -> TRIANGLE
        is Rectangle -> RECTANGLE
        is Ellipse -> ELLIPSE
        else -> ""
    }
    return ShapeJson(
        type = type,
        frame = frame.toJsonData(),
        uid = uid
    )
}

private fun AppFrame.toJsonData(): FrameJson =
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
    mutableListOf<IAppShape>().apply {
        shapes.forEach { shapeJson ->
            val shape = when (shapeJson.type) {
                RECTANGLE -> Rectangle(
                    frame = shapeJson.frame.toDomainData(),
                    uid = shapeJson.uid
                )
                TRIANGLE -> Triangle(
                    frame = shapeJson.frame.toDomainData(),
                    uid = shapeJson.uid
                )
                ELLIPSE -> Ellipse(
                    frame = shapeJson.frame.toDomainData(),
                    uid = shapeJson.uid
                )
                else -> throw RuntimeException("Incorrect file structure")
            }
            this.add(shape)
        }
    }

private fun FrameJson.toDomainData() =
    AppFrame(
        leftTop = leftTop.toDomainJson(),
        rightBottom = rightBottom.toDomainJson()
    )

private fun PointJson.toDomainJson() =
    AppPoint(x, y)
