package com.alexey.minay.ood.lab05.document

import com.alexey.minay.ood.lab05.commands.FileHelper
import com.alexey.minay.ood.lab05.common.History
import com.nhaarman.mockitokotlin2.mock
import org.junit.After
import org.junit.Test
import java.io.FileNotFoundException
import kotlin.test.assertEquals

class DocumentTest {

    private val mHistory = History()
    private val mFileHelper = mock<FileHelper>()
    private var mDocument = Document(mHistory, mFileHelper)

    @Test
    fun shouldInsertParagraph() {
        val text = "text"
        val position = 0
        mDocument.insertParagraph(text, position)
        assertEquals(text, mDocument.getItem(position).getParagraph()?.getText())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfInsertTextInIncorrectPosition() {
        mDocument.insertParagraph("text", 1)
    }

    @Test(expected = FileNotFoundException::class)
    fun shouldThrowExceptionIfImageNotFound() {
        mDocument.insertImage("incorrect/path/not_existed_image.jpg", 300, 200, 0)
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfInsertImageInIncorrectPath() {
        mDocument.insertImage("src/main/resources/hypa.jpg", 300, 200, 1)
    }

    @Test
    fun shouldDeleteItem() {
        mDocument.insertParagraph("text", 0)
        mDocument.insertParagraph("text", 1)
        mDocument.deleteItem(0)
        assertEquals(mDocument.getItemCount(), 1)
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfTryToDeleteDoesNotExistItem() {
        mDocument.deleteItem(0)
    }

    @Test
    fun shouldGetItem() {
        val position = 1
        mDocument.insertParagraph("text", 0)
        mDocument.insertParagraph("text", 1)
        mDocument.insertParagraph("text", 2)
        mDocument.insertParagraph("text", position)
        val item = mDocument.getItem(position)
        val items = mDocument.getItems()
        assertEquals(items[position], item)
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfGetUnExistItem() {
        val position = 6
        mDocument.insertParagraph("text", 0)
        mDocument.insertParagraph("text", 1)
        mDocument.insertParagraph("text", 2)
        mDocument.insertParagraph("text", position)
        val item = mDocument.getItem(position)
        val items = mDocument.getItems()
        assertEquals(items[position], item)
    }

    @Test
    fun shouldGetItemCount() {
        val count = 10
        for (i in 0 until count)
            mDocument.insertParagraph("text$i", i)
        assertEquals(count, mDocument.getItemCount())
    }

    @Test
    fun shouldSetTitle() {
        val title = "Title"
        mDocument.setTitle(title)
        assertEquals(title, mDocument.getTitle())
    }

    @Test
    fun shouldUndoSetTitle() {
        val title = "Title"
        mDocument.setTitle(title)
        mDocument.setTitle("Another title")
        mDocument.undo()
        assertEquals(title, mDocument.getTitle())
    }

    @Test
    fun shouldUndoInsertParagraph() {
        mDocument.insertParagraph("text", 0)
        mDocument.undo()
        assertEquals(0, mDocument.getItemCount())
    }

    @Test
    fun shouldUndoInsertImage() {
        mDocument.insertImage("src/main/resources/hypa.jpg", 300, 200, 0)
        mDocument.undo()
        assertEquals(0, mDocument.getItemCount())
    }

    @Test
    fun shouldUndoRedidAction() {
        mDocument.insertImage("src/main/resources/hypa.jpg", 300, 200, 0)
        mDocument.insertImage("src/main/resources/hypa.jpg", 300, 200, 1)
        mDocument.undo()
        mDocument.redo()
        mDocument.undo()
        assertEquals(1, mDocument.getItemCount())
    }

    @Test
    fun shouldRedoUndidTitle() {
        val title = "Title"
        mDocument.setTitle(title)
        mDocument.undo()
        mDocument.redo()
        assertEquals(title, mDocument.getTitle())
    }

    @Test
    fun shouldRedoUndidParagraph() {
        val text = "text"
        val position = 0
        mDocument.insertParagraph(text, position)
        mDocument.undo()
        mDocument.redo()
        assertEquals(text, mDocument.getItem(position).getParagraph()?.getText())
    }

    @After
    fun recreateDocument() {
        mDocument.close()
        mDocument = Document(mHistory, mFileHelper)
    }

}