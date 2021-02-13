package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.Image
import org.junit.Test
import kotlin.test.assertEquals

class ResizeImageCommandTest {

    @Test
    fun shouldResizeImageHeight() {
        val oldWidth = 200
        val oldHeight = 300
        val newHeight = 400
        val image = Image("name", "path", oldWidth, oldHeight)
        val item = DocumentItem(image)
        ResizeImageCommand(item, oldWidth, newHeight).execute()
        assertEquals(newHeight, item.getImage()?.getHeight())
    }

    @Test
    fun shouldResizeImageWidth() {
        val oldWidth = 200
        val oldHeight = 300
        val newWidth = 400
        val image = Image(
            name = "name",
            path = "path",
            width = oldWidth,
            height = oldHeight
        )
        val item = DocumentItem(image)
        ResizeImageCommand(
            documentItem = item,
            width = newWidth,
            height = oldHeight
        ).execute()
        assertEquals(newWidth, item.getImage()?.getWidth())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfItemNotImage() {
        val oldWidth = 200
        val newHeight = 400
        val item = DocumentItem()
        ResizeImageCommand(item, oldWidth, newHeight).execute()
    }

    @Test
    fun shouldUnExecuteCommand() {
        val oldWidth = 200
        val oldHeight = 300
        val newHeight = 400
        val image = Image("name", "path", oldWidth, oldHeight)
        val item = DocumentItem(image)
        val command = ResizeImageCommand(item, oldWidth, newHeight)
        command.execute()
        command.unExecute()
        assertEquals(oldHeight, item.getImage()?.getHeight())
    }

}