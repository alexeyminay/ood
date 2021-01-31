package com.alexey.minay.ood.lab09.infrastructure

data class ShapesJson(
        val shapes: List<ShapeJson>
)

data class ShapeJson(
        val type: String,
        val frame: FrameJson
)

data class FrameJson(
        val leftTop: PointJson,
        val rightBottom: PointJson
)

data class PointJson(
        val x: Double,
        val y: Double
)

data class StyleJson(
        val strokeColor: ColorJson,
        val fillColor: ColorJson
)

data class ColorJson(
        val red: Double,
        val green: Double,
        val blue: Double
)