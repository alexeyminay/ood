package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class ResizeImageCommandTest {

    private val mDocument = mock<IDocument>()
    private val mWidth = 20
    private val mHeight = 30
    private lateinit var mResizeImageCommand: ResizeImageCommand

    @Test
    fun shouldResizeImageAtTheLastPosition() {
        mResizeImageCommand = ResizeImageCommand(mDocument, mWidth, mHeight)
        mResizeImageCommand.execute()
        verify(mDocument).getDocumentSize()
        verify(mDocument).resizeImage(mHeight, mWidth, mDocument.getDocumentSize())
    }

    @Test
    fun shouldResizeImageAtTheSpecifiedPosition() {
        val position = 1
        mResizeImageCommand = ResizeImageCommand(mDocument, mWidth, mHeight, position)
        mResizeImageCommand.execute()
        verify(mDocument).resizeImage(mHeight, mWidth, position)
    }

}