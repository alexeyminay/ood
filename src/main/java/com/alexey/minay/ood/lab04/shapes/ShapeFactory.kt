package com.alexey.minay.ood.lab04.shapes

import com.alexey.minay.ood.lab04.IShapeFactory

class ShapeFactory : IShapeFactory {

    override fun createShape(description: String): Shape {
        val splittedDescription = description.split(DELIMITER)
        if (splittedDescription.isEmpty()) throw RuntimeException("Invalid params")
        return when (splittedDescription[SHAPE_TYPE_INDEX].toLowerCase()) {
            "rectangle" -> createRectangle(splittedDescription)
            "triangle" -> createTriangle(splittedDescription)
            "ellipse" -> createEllipse(splittedDescription)
            //"regularPolygon" -> createRegularPolygon(splittedDescription)
            else -> throw RuntimeException("Unknown shape type")
        }
    }

    private fun createRectangle(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != RECTANGLE_DESCRIPTION_SIZE) throw RuntimeException("Incorrect rectangle description")
        val leftTop = splittedDescription[RECTANGLE_LEFT_TOP_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect rectangle leftTop description")
        val rightBottom = splittedDescription[RECTANGLE_RIGHT_BOTTOM_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect rectangle rightBottom description")
        return Rectangle(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                leftTop = leftTop,
                rightBottom = rightBottom
        )
    }

    private fun createTriangle(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != TRIANGLE_DESCRIPTION_SIZE) throw RuntimeException("Incorrect triangle description")
        val vertex1 = splittedDescription[TRIANGLE_VERTEX_1_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex1 description")
        val vertex2 = splittedDescription[TRIANGLE_VERTEX_2_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex2 description")
        val vertex3 = splittedDescription[TRIANGLE_VERTEX_3_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect triangle vertex3 description")
        return Triangle(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                vertex1 = vertex1,
                vertex2 = vertex2,
                vertex3 = vertex3
        )
    }

    private fun createEllipse(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != ELLIPSE_DESCRIPTION_SIZE) throw RuntimeException("Incorrect ellipse description")
        val center = splittedDescription[ELLIPSE_CENTER_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect ellipse center description")
        val horizontalRadius = splittedDescription[ELLIPSE_HORIZONTAL_RADIUS_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect ellipse horizontal radius description")
        val verticalRadius = splittedDescription[ELLIPSE_VERTICAL_RADIUS_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect ellipse vertical radius description")
        return Ellipse(
                color = parseColor(splittedDescription[COLOR_INDEX]),
                center = center,
                horizontalRadius = horizontalRadius,
                verticalRadius = verticalRadius
        )
    }

    /*private fun createRegularPolygon(splittedDescription: List<String>): Shape {
        if (splittedDescription.size != REGULAR_POLYGON_SIZE) throw RuntimeException("Incorrect regular polygon description")
        val vertexCount = splittedDescription[REGULAR_POLYGON_VERTEX_COUNT_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect regular polygon vertex count description")
        val center = splittedDescription[REGULAR_POLYGON_CENTER_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect regular polygon center description")
        val radius = splittedDescription[REGULAR_POLYGON_RADIUS_INDEX].toIntOrNull()
                ?: throw RuntimeException("Incorrect regular polygon radius description")
        return RegularPolygon(
                color = parseColor(splittedDescription[COLOR_INDEX]),

        )
    }*/

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

        private const val RECTANGLE_DESCRIPTION_SIZE = 4
        private const val RECTANGLE_LEFT_TOP_INDEX = 2
        private const val RECTANGLE_RIGHT_BOTTOM_INDEX = 3

        private const val TRIANGLE_DESCRIPTION_SIZE = 5
        private const val TRIANGLE_VERTEX_1_INDEX = 2
        private const val TRIANGLE_VERTEX_2_INDEX = 3
        private const val TRIANGLE_VERTEX_3_INDEX = 4

        private const val ELLIPSE_DESCRIPTION_SIZE = 5
        private const val ELLIPSE_CENTER_INDEX = 2
        private const val ELLIPSE_HORIZONTAL_RADIUS_INDEX = 3
        private const val ELLIPSE_VERTICAL_RADIUS_INDEX = 4

        private const val REGULAR_POLYGON_SIZE = 5
        private const val REGULAR_POLYGON_VERTEX_COUNT_INDEX = 2
        private const val REGULAR_POLYGON_CENTER_INDEX = 3
        private const val REGULAR_POLYGON_RADIUS_INDEX = 4

    }

}