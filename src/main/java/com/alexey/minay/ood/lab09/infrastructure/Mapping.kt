package com.alexey.minay.ood.lab09.infrastructure

import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.shapes.*
import com.alexey.minay.ood.lab09.domain.IShape
import com.alexey.minay.ood.lab09.domain.style.Color
import com.alexey.minay.ood.lab09.domain.style.Style

private const val TRIANGLE = "triangle"
private const val RECTANGLE = "rectangle"
private const val ELLIPSE = "ellipse"

fun IShape.toJsonData(): ShapeJson {
    val type = when (this) {
        is Triangle -> TRIANGLE
        is Rectangle -> RECTANGLE
        is Ellipse -> ELLIPSE
        else -> ""
    }
    return ShapeJson(
            type = type,
            frame = frame.toJsonData(),
            style = shapeStyle.toJsonData()
    )
}

private fun Frame.toJsonData(): FrameJson =
        FrameJson(
                PointJson(
                        x = leftTop.x,
                        y = leftTop.y
                ),
                PointJson(x = rightBottom.x,
                        y = rightBottom.y)
        )

private fun Style.Shape.toJsonData(): StyleJson =
        StyleJson(
                strokeColor = strokeColor.toJsonData(),
                fillColor = fillColor.toJsonData()
        )

private fun Color.toJsonData() =
        ColorJson(red, green, blue)

fun ShapesJson.toDomainData() =
        mutableListOf<IShape>().apply {
            shapes.forEach { shapeJson ->
                val shape = when (shapeJson.type) {
                    RECTANGLE -> Rectangle(
                            frame = shapeJson.frame.toDomainData(),
                            shapeStyle = shapeJson.style.toDomainData()
                    )
                    TRIANGLE -> Triangle(
                            frame = shapeJson.frame.toDomainData(),
                            shapeStyle = shapeJson.style.toDomainData()
                    )
                    ELLIPSE -> Ellipse(
                            frame = shapeJson.frame.toDomainData(),
                            shapeStyle = shapeJson.style.toDomainData()
                    )
                    else -> throw RuntimeException("Incorrect file structure")
                }
                this.add(shape)
            }
        }

private fun StyleJson.toDomainData() =
        Style.Shape(
                strokeColor = strokeColor.toDomainData(),
                fillColor = fillColor.toDomainData()
        )

private fun ColorJson.toDomainData() =
        Color(red, green, blue)

private fun FrameJson.toDomainData() =
        Frame(
                leftTop = leftTop.toDomainJson(),
                rightBottom = rightBottom.toDomainJson()
        )

private fun PointJson.toDomainJson() =
        Point(x, y)
