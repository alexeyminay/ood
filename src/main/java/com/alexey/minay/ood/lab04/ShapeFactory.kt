package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.*

class ShapeFactory : IShapeFactory {

    override fun createShape(description: String): Shape {
        val splittedDescription = description.split(DELIMITER)
        if (splittedDescription.isEmpty()) throw RuntimeException("Invalid params")
        return when (splittedDescription[SHAPE_TYPE_INDEX].toLowerCase()) {
            "rectangle" -> createRectangle(splittedDescription)
            "triangle" -> createTriangle(splittedDescription)
            "ellipse" -> createEllipse(splittedDescription)
            "regularpolygon" -> createRegularPolygon(splittedDescription)
            else -> throw RuntimeException("Unknown group type")
        }
    }

    private fun createRectangle(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != RECTANGLE_DESCRIPTION_SIZE) throw RuntimeException("Incorrect rectangle description")
        val leftTopX = splittedDescription[RECTANGLE_LEFT_TOP_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect rectangle leftTopX description")
        val leftTopY = splittedDescription[RECTANGLE_LEFT_TOP_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect rectangle leftTopY description")
        val rightBottomX = splittedDescription[RECTANGLE_RIGHT_BOTTOM_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect rectangle rightBottomX description")
        val rightBottomY = splittedDescription[RECTANGLE_RIGHT_BOTTOM_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect rectangle rightBottomY description")
        return Rectangle(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                leftTop = Point(leftTopX, leftTopY),
                rightBottom = Point(rightBottomX, rightBottomY)
        )
    }

    private fun createTriangle(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != TRIANGLE_DESCRIPTION_SIZE) throw RuntimeException("Incorrect triangle description")
        val vertex1X = splittedDescription[TRIANGLE_VERTEX_1_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex1X description")
        val vertex1Y = splittedDescription[TRIANGLE_VERTEX_1_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex1Y description")
        val vertex2X = splittedDescription[TRIANGLE_VERTEX_2_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex2X description")
        val vertex2Y = splittedDescription[TRIANGLE_VERTEX_2_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex2Y description")
        val vertex3X = splittedDescription[TRIANGLE_VERTEX_3_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex3X description")
        val vertex3Y = splittedDescription[TRIANGLE_VERTEX_3_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex3Y description")
        return Triangle(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                vertex1 = Point(vertex1X, vertex1Y),
                vertex2 = Point(vertex2X, vertex2Y),
                vertex3 = Point(vertex3X, vertex3Y)
        )
    }

    private fun createEllipse(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != ELLIPSE_DESCRIPTION_SIZE) throw RuntimeException("Incorrect ellipse description")
        val centerX = splittedDescription[ELLIPSE_CENTER_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect ellipse centerX description")
        val centerY = splittedDescription[ELLIPSE_CENTER_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect ellipse centerY description")
        val horizontalRadius = splittedDescription[ELLIPSE_HORIZONTAL_RADIUS_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect ellipse horizontal radius description")
        val verticalRadius = splittedDescription[ELLIPSE_VERTICAL_RADIUS_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect ellipse vertical radius description")
        return Ellipse(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                center = Point(centerX, centerY),
                horizontalRadius = horizontalRadius,
                verticalRadius = verticalRadius
        )
    }

    private fun createRegularPolygon(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != REGULAR_POLYGON_SIZE) throw RuntimeException("Incorrect regular polygon description")
        val vertexCount = splittedDescription[REGULAR_POLYGON_VERTEX_COUNT_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect regular polygon vertex count description")
        if (vertexCount < 2) throw RuntimeException("Incorrect vertex count")
        val centerX = splittedDescription[REGULAR_POLYGON_CENTER_X_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect regular polygon centerX description")
        val centerY = splittedDescription[REGULAR_POLYGON_CENTER_Y_INDEX].toDoubleOrNull()
                ?: throw RuntimeException("Incorrect regular polygon centerY description")
        val radius = splittedDescription[REGULAR_POLYGON_RADIUS_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect regular polygon radius description")
        return RegularPolygon(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                center = Point(centerX, centerY),
                vertexCount = vertexCount,
                radius = radius
        )
    }

    private fun parseColor(color: String) =
            when (color.toLowerCase()) {
                "green" -> Color.GREEN
                "red" -> Color.RED
                "blue" -> Color.BLUE
                "yellow" -> Color.YELLOW
                "pink" -> Color.PINK
                "black" -> Color.BLACK
                else -> throw RuntimeException("Incorrect color description")
            }

    companion object {

        private const val DELIMITER = " "
        private const val SHAPE_TYPE_INDEX = 0
        private const val COLOR_INDEX = 1

        private const val RECTANGLE_DESCRIPTION_SIZE = 6
        private const val RECTANGLE_LEFT_TOP_X_INDEX = 2
        private const val RECTANGLE_LEFT_TOP_Y_INDEX = 3
        private const val RECTANGLE_RIGHT_BOTTOM_X_INDEX = 4
        private const val RECTANGLE_RIGHT_BOTTOM_Y_INDEX = 5

        private const val TRIANGLE_DESCRIPTION_SIZE = 8
        private const val TRIANGLE_VERTEX_1_X_INDEX = 2
        private const val TRIANGLE_VERTEX_1_Y_INDEX = 3
        private const val TRIANGLE_VERTEX_2_X_INDEX = 4
        private const val TRIANGLE_VERTEX_2_Y_INDEX = 5
        private const val TRIANGLE_VERTEX_3_X_INDEX = 6
        private const val TRIANGLE_VERTEX_3_Y_INDEX = 7

        private const val ELLIPSE_DESCRIPTION_SIZE = 6
        private const val ELLIPSE_CENTER_X_INDEX = 2
        private const val ELLIPSE_CENTER_Y_INDEX = 3
        private const val ELLIPSE_HORIZONTAL_RADIUS_INDEX = 4
        private const val ELLIPSE_VERTICAL_RADIUS_INDEX = 5

        private const val REGULAR_POLYGON_SIZE = 6
        private const val REGULAR_POLYGON_VERTEX_COUNT_INDEX = 2
        private const val REGULAR_POLYGON_CENTER_X_INDEX = 3
        private const val REGULAR_POLYGON_CENTER_Y_INDEX = 4
        private const val REGULAR_POLYGON_RADIUS_INDEX = 5

    }

}