package com.alexey.minay.ood.lab04.shapes

import org.junit.Assert.assertEquals
import org.junit.Test

class ShapeFactoryTest {

    private val mShapeFactory = ShapeFactory()

    @Test
    fun shouldDrawRectangle() {
        val rectangle = mShapeFactory.createShape("rectangle green 40 50 70 80")
        assertEquals(rectangle, Rectangle(
                color = Color.GREEN,
                leftTop = Point(40.0, 50.0),
                rightBottom = Point(70.0, 80.0)
        ))
    }

    @Test
    fun shouldDrawTriangle() {
        val triangle = mShapeFactory.createShape("triangle red 40 50 80 90 40 120")
        assertEquals(triangle, Triangle(
                color = Color.RED,
                vertex1 = Point(40.0, 50.0),
                vertex2 = Point(80.0, 90.0),
                vertex3 = Point(40.0, 120.0)
        ))
    }

    @Test
    fun shouldDrawEllipse() {
        val ellipse = mShapeFactory.createShape("ellipse blue 120 140 100 80")
        assertEquals(ellipse, Ellipse(
                color = Color.BLUE,
                center = Point(120.0, 140.0),
                horizontalRadius = 100,
                verticalRadius = 80
        ))
    }

    @Test
    fun shouldDrawPolygon() {
        val polygon = mShapeFactory.createShape("regularPolygon yellow 17 150 160 70")
        assertEquals(polygon, RegularPolygon(
                color = Color.YELLOW,
                vertexCount = 17,
                center = Point(150.0, 160.0),
                radius = 70
        ))
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfIncorrectParamsOrder() {
        mShapeFactory.createShape("rectangle 40 50 70 80 green")
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfIncorrectParamsCount() {
        mShapeFactory.createShape("rectangle 40 50 70 80")
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfIncorrectType() {
        mShapeFactory.createShape("circle red 40 50 80 90 40 120")
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfUnknown() {
        mShapeFactory.createShape("regularPolygon white 17 150 160 70")
    }

}