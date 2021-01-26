package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.ICanvas
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class PainterTest {

    private val mPainter = Painter()
    private val mCanvas = mock<ICanvas>()
    private val mShape1 = mock<Shape>()
    private val mShape2 = mock<Shape>()
    private val mDraft = PictureDraft(mutableListOf(mShape1, mShape2))

    @Test
    fun shouldDrawShape() {
        mPainter.drawPicture(mDraft, mCanvas)
        verify(mShape1).draw(mCanvas)
        verify(mShape2).draw(mCanvas)
    }

}