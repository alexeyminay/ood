package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.alexey.minay.ood.lab05.document.Image
import org.junit.Test
import kotlin.test.assertEquals

class ResizeImageCommandTest {

    private val mDocumentItems = mutableListOf<IDocumentItem>()

    @Test
    fun shouldResizeImageHeight() {
        val oldWidth = 200
        val oldHeight = 300
        val position = 0
        val newHeight = 400
        val image = Image("name", "path", oldWidth, oldHeight)
        mDocumentItems.add(DocumentItem(image))
        ResizeImageCommand(mDocumentItems, oldWidth, newHeight, position).execute()
        assertEquals(newHeight, mDocumentItems[position].getImage()?.getHeight())
    }

    @Test
    fun shouldResizeImageWidth() {
        val oldWidth = 200
        val oldHeight = 300
        val position = 0
        val newWidth = 400
        val image = Image(name = "name",
                path = "path",
                width = oldWidth,
                height = oldHeight)
        mDocumentItems.add(DocumentItem(image))
        ResizeImageCommand(documentItems = mDocumentItems,
                width = newWidth,
                height = oldHeight,
                position = position).execute()
        assertEquals(newWidth, mDocumentItems[position].getImage()?.getWidth())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfIncorrectPosition() {
        val oldWidth = 200
        val oldHeight = 300
        val position = 1
        val newHeight = 400
        val image = Image("name", "path", oldWidth, oldHeight)
        mDocumentItems.add(DocumentItem(image))
        ResizeImageCommand(mDocumentItems, oldWidth, newHeight, position).execute()
        assertEquals(newHeight, mDocumentItems[position].getImage()?.getHeight())
    }

    @Test
    fun shouldUnExecuteCommand() {
        val oldWidth = 200
        val oldHeight = 300
        val position = 0
        val newHeight = 400
        val image = Image("name", "path", oldWidth, oldHeight)
        mDocumentItems.add(DocumentItem(image))
        val command = ResizeImageCommand(mDocumentItems, oldWidth, newHeight, position)
        command.execute()
        command.unExecute()
        assertEquals(oldHeight, mDocumentItems[position].getImage()?.getHeight())
    }

}