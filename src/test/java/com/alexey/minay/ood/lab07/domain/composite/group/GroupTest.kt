package com.alexey.minay.ood.lab07.domain.composite.group

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.*
import com.alexey.minay.ood.lab07.domain.composite.shapes.Ellipse
import com.alexey.minay.ood.lab07.domain.composite.shapes.Triangle
import com.alexey.minay.ood.lab07.domain.composite.style.SimpleStyle
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals
import kotlin.test.assertNull

class GroupTest {

    private val mCanvas = mock<ICanvas>()
    private var mGroup = Group()

    @Test
    fun shouldDrawAllAddedShapes() {
        val shape1 = mock<IShape>()
        val shape2 = mock<IShape>()

        mGroup.insertShape(shape1, mGroup.getShapeCount())
        mGroup.insertShape(shape2, mGroup.getShapeCount())

        mGroup.draw(mCanvas)

        verify(shape1).draw(mCanvas)
        verify(shape2).draw(mCanvas)
    }

    @Test
    fun shouldAddAllShapes() {
        val shapes = mutableListOf(mock<IShape>(), mock(), mock(), mock())

        shapes.forEachIndexed { index, shape ->
            mGroup.insertShape(shape, index)
        }

        assertEquals(shapes.size, mGroup.getShapeCount())
    }

    @Test
    fun shouldRemoveShapes() {
        val shapes = mutableListOf(mock<IShape>(), mock(), mock(), mock())

        shapes.forEachIndexed { index, shape ->
            mGroup.insertShape(shape, index)
        }
        mGroup.removeAt(0)
        mGroup.removeAt(0)

        assertEquals(2, mGroup.getShapeCount())
    }

    @Test
    fun shouldHaveFrameLikeShapeFrame() {
        val shape = mock<IShape>()
        val frame = Frame(1.0, 3.0, 1.0, 3.0)
        Mockito.`when`(shape.frame).thenReturn(frame)

        mGroup.insertShape(shape, 0)
        assertEquals(frame, mGroup.frame)
    }

    @Test
    fun shouldHaveFrameIncludingAllInsertedShapeFrames() {
        val minLeft = 1.0
        val maxRight = 4.0
        val minTop = 1.0
        val maxBottom = 4.0
        val shape1 = mock<IShape>()
        val frame1 = Frame(minLeft, 3.0, minTop, 3.0)
        Mockito.`when`(shape1.frame).thenReturn(frame1)

        val shape2 = mock<IShape>()
        val frame2 = Frame(2.0, maxRight, 2.0, maxBottom)
        Mockito.`when`(shape2.frame).thenReturn(frame2)

        mGroup.insertShape(shape1, 0)
        mGroup.insertShape(shape2, 0)

        val expectedFrame = Frame(minLeft, maxRight, minTop, maxBottom)
        assertEquals(expectedFrame, mGroup.frame)
    }

    @Test
    fun shouldReturnCommonFillColor() {
        val commonColor = RGBAColor.YELLOW
        val group = createGroup(commonColor, commonColor)
        assertEquals(RGBAColor.YELLOW, group.style.fillStyle?.color)
    }

    @Test
    fun shouldReturnCommonLineColor() {
        val commonColor = RGBAColor.YELLOW
        val group = createGroup(commonColor, commonColor)
        assertEquals(RGBAColor.YELLOW, group.style.lineStyle?.color)
    }

    @Test
    fun shouldReturnNullIfShapesHaveDifferentFillColor() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(firstColor, secondColor)
        assertNull(group.style.fillStyle)
    }

    @Test
    fun shouldReturnNullIfShapesHaveDifferentLineColor() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(firstColor, secondColor)
        assertNull(group.style.lineStyle)
    }

    @Test
    fun shouldSetCommonFillStyle() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(firstColor, secondColor)
        val commonColor = RGBAColor.BLUE
        group.style.fillStyle = FillStyle(true, commonColor)
        assertEquals(commonColor, group.style.fillStyle?.color)
    }

    @Test
    fun shouldSetCommonLineStyle() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(firstColor, secondColor)
        val commonColor = RGBAColor.BLUE
        group.style.lineStyle = LineStyle(true, commonColor)
        assertEquals(commonColor, group.style.lineStyle?.color)
    }

    @Test
    fun shouldReturnNullIfSetDifferentColorIntoShape() {
        val firstShape = Triangle(
            style = SimpleStyle(
                fillStyle = FillStyle(true, RGBAColor.YELLOW),
                lineStyle = LineStyle(true, RGBAColor.YELLOW)
            ),
            shapeFrame = Frame(20.0, 260.0, 150.0, 250.0)
        )
        val secondShape = Ellipse(
            style = SimpleStyle(
                fillStyle = FillStyle(true, RGBAColor.YELLOW),
                lineStyle = LineStyle(true, RGBAColor.YELLOW)
            ),
            shapeFrame = Frame(700.0, 800.0, 30.0, 130.0)
        )
        val group = Group().apply {
            insertShape(firstShape, 0)
            insertShape(secondShape, 1)
        }

        val newFirstShapeFillColor = RGBAColor.RED
        firstShape.style.fillStyle = FillStyle(true, newFirstShapeFillColor)

        assertNull(group.style.fillStyle)
    }

    @After
    fun recreateGroup() {
        mGroup = Group()
    }

    private fun createGroup(firstShapeColor: RGBAColor, secondShapeColor: RGBAColor) =
        Group().apply {
            val firstShape = Triangle(
                style = SimpleStyle(
                    fillStyle = FillStyle(true, firstShapeColor),
                    lineStyle = LineStyle(true, firstShapeColor)
                ),
                shapeFrame = Frame(20.0, 260.0, 150.0, 250.0)
            )
            val secondShape = Ellipse(
                style = SimpleStyle(
                    fillStyle = FillStyle(true, secondShapeColor),
                    lineStyle = LineStyle(true, secondShapeColor)
                ),
                shapeFrame = Frame(700.0, 800.0, 30.0, 130.0)
            )
            insertShape(firstShape, 0)
            insertShape(secondShape, 1)
        }

}