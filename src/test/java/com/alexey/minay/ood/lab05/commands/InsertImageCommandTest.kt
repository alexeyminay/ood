package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.FileHelper
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class InsertImageCommandTest {

    private val mDocumentItem = mutableListOf<IDocumentItem>()
    private val mFileHelper = mock<FileHelper>()

    @Test
    fun shouldInsertImageWithWidth() {
        val path = "path"
        val width = 300
        val height = 200
        val position = 0
        val insertCommand = InsertImageCommand(
                documentItems = mDocumentItem,
                path = path,
                width = width,
                height = height,
                position = position,
                fileHelper = mFileHelper
        )
        insertCommand.execute()
        assertEquals(width, mDocumentItem[0].getImage()?.getWidth())
    }

    @Test
    fun shouldInsertImageWithHeight() {
        val path = "path"
        val width = 300
        val height = 200
        val position = 0
        val insertCommand = InsertImageCommand(
                documentItems = mDocumentItem,
                path = path,
                width = width,
                height = height,
                position = position,
                fileHelper = mFileHelper
        )
        insertCommand.execute()
        assertEquals(height, mDocumentItem[0].getImage()?.getHeight())
    }

    @Test
    fun shouldInsertImage() {
        val path = "path"
        val width = 300
        val height = 200
        val position = 0
        val insertCommand = InsertImageCommand(
                documentItems = mDocumentItem,
                path = path,
                width = width,
                height = height,
                position = position,
                fileHelper = mFileHelper
        )
        insertCommand.execute()
        val position2 = 1
        val insertCommand2 = InsertImageCommand(
                documentItems = mDocumentItem,
                path = path,
                width = width,
                height = height,
                position = position2,
                fileHelper = mFileHelper
        )
        insertCommand2.execute()
        assertEquals(2, mDocumentItem.size)
    }


    @Test
    fun shouldDeleteInsertedImage() {
        val path = "path"
        val width = 300
        val height = 200
        val position = 0
        val insertCommand = InsertImageCommand(
                documentItems = mDocumentItem,
                path = path,
                width = width,
                height = height,
                position = position,
                fileHelper = mFileHelper
        )
        insertCommand.execute()
        insertCommand.unExecute()
        assertEquals(0, mDocumentItem.size)
    }

}