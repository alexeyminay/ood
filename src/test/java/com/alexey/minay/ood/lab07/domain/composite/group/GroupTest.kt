package com.alexey.minay.ood.lab07.domain.composite.group

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.Group
import com.alexey.minay.ood.lab07.domain.composite.IShape
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor
import com.alexey.minay.ood.lab07.domain.composite.shapes.Ellipse
import com.alexey.minay.ood.lab07.domain.composite.shapes.Triangle
import com.alexey.minay.ood.lab07.domain.composite.style.SimpleFillStyle
import com.alexey.minay.ood.lab07.domain.composite.style.SimpleLineStyle
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
        val group = createGroup(
            firstShapeColor = commonColor,
            secondShapeColor = commonColor
        )
        assertEquals(RGBAColor.YELLOW, group.fillStyle.color)
    }

    @Test
    fun shouldReturnCommonLineColor() {
        val commonColor = RGBAColor.YELLOW
        val group = createGroup(
            firstShapeColor = commonColor,
            secondShapeColor = commonColor
        )
        assertEquals(RGBAColor.YELLOW, group.lineStyle.color)
    }

    @Test
    fun shouldReturnNullIfShapesHaveDifferentFillColor() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(
            firstShapeColor = firstColor,
            secondShapeColor = secondColor
        )
        assertNull(group.fillStyle.color)
    }

    @Test
    fun shouldReturnNullIfShapesHaveDifferentLineColor() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(
            firstShapeColor = firstColor,
            secondShapeColor = secondColor
        )
        assertNull(group.lineStyle.color)
    }

    @Test
    fun shouldSetCommonFillColor() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(
            firstShapeColor = firstColor,
            secondShapeColor = secondColor
        )
        val commonColor = RGBAColor.BLUE
        group.fillStyle.color = commonColor
        assertEquals(commonColor, group.fillStyle.color)
    }

    @Test
    fun shouldSetCommonLineStyle() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.GREEN
        val group = createGroup(
            firstShapeColor = firstColor,
            secondShapeColor = secondColor
        )
        val commonColor = RGBAColor.BLUE
        group.lineStyle.color = commonColor
        assertEquals(commonColor, group.lineStyle.color)
    }

    @Test
    fun shouldReturnNullIfSetDifferentFillColorIntoShape() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.YELLOW
        val group = createGroup(
            firstShapeColor = firstColor,
            secondShapeColor = secondColor
        )

        val newFirstShapeFillColor = RGBAColor.RED
        group.getShapeIndexAt(0).fillStyle.color = newFirstShapeFillColor

        assertNull(group.fillStyle.color)
    }

    @Test
    fun shouldReturnNullIfSetDifferentLineColorIntoShape() {
        val firstColor = RGBAColor.YELLOW
        val secondColor = RGBAColor.YELLOW
        val group = createGroup(
            firstShapeColor = firstColor,
            secondShapeColor = secondColor
        )

        val newFirstShapeFillColor = RGBAColor.RED
        group.getShapeIndexAt(0).lineStyle.color = newFirstShapeFillColor

        assertNull(group.lineStyle.color)
    }

    @Test
    fun `should return enable if both fill style enable`() {
        val isEnable = true
        val group = createGroup(
            isFirstShapeFillStyleEnable = isEnable,
            isSecondShapeFillStyleEnable = isEnable
        )
        assert(group.fillStyle.isEnable == true)
    }

    @Test
    fun `should return null if fill style has different value`() {
        val group = createGroup(
            isFirstShapeFillStyleEnable = false,
            isSecondShapeFillStyleEnable = true
        )
        assertNull(group.fillStyle.isEnable)
    }

    @Test
    fun `should return enable if both line style enable`() {
        val isEnable = true
        val group = createGroup(
            isFirstShapeLineStyleEnable = isEnable,
            isSecondShapeLineStyleEnable = isEnable
        )
        assert(group.lineStyle.isEnable == isEnable)
    }

    @Test
    fun `should return null if line style has different value`() {
        val group = createGroup(
            isFirstShapeLineStyleEnable = false,
            isSecondShapeLineStyleEnable = true
        )
        assertNull(group.lineStyle.isEnable)
    }

    @Test
    fun `should return common line width`() {
        val lineWidth = 4.0
        val group = createGroup(
            firstShapeLineWidth = lineWidth,
            secondShapeLineWidth = lineWidth
        )
        assertEquals(lineWidth, group.lineStyle.lineWidth)
    }

    @Test
    fun `should return null if different line width`() {
        val group = createGroup(
            firstShapeLineWidth = 4.0,
            secondShapeLineWidth = 3.0
        )
        assertNull(group.lineStyle.lineWidth)
    }

    @After
    fun recreateGroup() {
        mGroup = Group()
    }

    private fun createGroup(
        firstShapeColor: RGBAColor = RGBAColor.BLUE,
        secondShapeColor: RGBAColor = RGBAColor.RED,
        isFirstShapeFillStyleEnable: Boolean = true,
        isFirstShapeLineStyleEnable: Boolean = true,
        isSecondShapeFillStyleEnable: Boolean = true,
        isSecondShapeLineStyleEnable: Boolean = true,
        firstShapeLineWidth: Double = 3.0,
        secondShapeLineWidth: Double = 3.0
    ) =
        Group().apply {
            val firstShape = Triangle(
                fillStyle = SimpleFillStyle(isFirstShapeFillStyleEnable, firstShapeColor),
                lineStyle = SimpleLineStyle(isFirstShapeLineStyleEnable, firstShapeColor, firstShapeLineWidth),
                shapeFrame = Frame(20.0, 260.0, 150.0, 250.0)
            )
            val secondShape = Ellipse(
                fillStyle = SimpleFillStyle(isSecondShapeFillStyleEnable, secondShapeColor),
                lineStyle = SimpleLineStyle(isSecondShapeLineStyleEnable, secondShapeColor, secondShapeLineWidth),
                shapeFrame = Frame(700.0, 800.0, 30.0, 130.0)
            )
            insertShape(firstShape, 0)
            insertShape(secondShape, 1)
        }


}