package com.alexey.minay.ood.lab07.domain.composite.group

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.Group
import com.alexey.minay.ood.lab07.domain.composite.IShape
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

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
    fun shouldHasFrameLikeShapeFrame() {
        val shape = mock<IShape>()
        val frame = Frame(1.0, 3.0, 1.0, 3.0)
        Mockito.`when`(shape.frame).thenReturn(frame)

        mGroup.insertShape(shape, 0)
        assertEquals(frame, mGroup.frame)
    }

    @Test
    fun shouldHasFrameIncludingAllInsertedShapeFrames() {
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

    @After
    fun recreateGroup() {
        mGroup = Group()
    }

}