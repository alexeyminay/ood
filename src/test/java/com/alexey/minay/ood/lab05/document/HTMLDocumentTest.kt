package com.alexey.minay.ood.lab05.document

import org.junit.After
import org.junit.Test
import java.io.File
import java.io.FileNotFoundException
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class HTMLDocumentTest {

    private var mHtmlDocument = HTMLDocument()

    @Test
    fun shouldInsertParagraph() {
        val paragraph = mHtmlDocument.insertParagraph("text", 0)
        assertEquals(paragraph, mHtmlDocument.getItem(0).getParagraph())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfInsertTextInIncorrectPosition() {
        mHtmlDocument.insertParagraph("text", 1)
    }

    @Test
    fun shouldReplaceText() {
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertParagraph("text1", 1)
        val paragraph = mHtmlDocument.replaceTextInParagraph("text2", 1)
        assertEquals(paragraph, mHtmlDocument.getItem(1).getParagraph())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfReplaceTextInIncorrectPosition() {
        mHtmlDocument.replaceTextInParagraph("text2", 0)
    }

    @Test
    fun shouldInsertImage() {
        val image = mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        assertEquals(image, mHtmlDocument.getItem(0).getImage())
    }

    @Test(expected = FileNotFoundException::class)
    fun shouldThrowExceptionIfImageNotFound() {
        mHtmlDocument.insertImages("incorrect/path/not_existed_image.jpg", 300, 200, 0)
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfInsertImageInIncorrectPath() {
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 1)
    }

    @Test
    fun shouldResizeImage() {
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        val image = mHtmlDocument.resizeImage(200, 300, 0)
        assertEquals(image, mHtmlDocument.getItem(0).getImage())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfResizeUnExistImage() {
        mHtmlDocument.resizeImage(200, 300, 0)
    }

    @Test
    fun shouldDeleteItem() {
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertParagraph("text", 1)
        mHtmlDocument.deleteItem(0)
        assertEquals(mHtmlDocument.getItemCount(), 1)
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfTryToDeleteDoesNotExistItem() {
        mHtmlDocument.deleteItem(0)
    }

    @Test
    fun shouldGetItem() {
        val position = 1
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertParagraph("text", 1)
        mHtmlDocument.insertParagraph("text", 2)
        mHtmlDocument.insertParagraph("text", position)
        val item = mHtmlDocument.getItem(position)
        val items = mHtmlDocument.getItems()
        assertEquals(items[position], item)
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfGetUnExistItem() {
        val position = 6
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertParagraph("text", 1)
        mHtmlDocument.insertParagraph("text", 2)
        mHtmlDocument.insertParagraph("text", position)
        val item = mHtmlDocument.getItem(position)
        val items = mHtmlDocument.getItems()
        assertEquals(items[position], item)
    }

    @Test
    fun shouldGetItemCount() {
        val count = 10
        for (i in 0 until count)
            mHtmlDocument.insertParagraph("text$i", i)
        assertEquals(count, mHtmlDocument.getItemCount())
    }

    @Test
    fun shouldSetTitle() {
        val title = "Title"
        mHtmlDocument.setTitle(title)
        assertEquals(title, mHtmlDocument.getTitle())
    }

    @Test
    fun shouldCanUndo() {
        val title = "Title"
        mHtmlDocument.setTitle(title)
        assert(mHtmlDocument.canUndo())
    }

    @Test
    fun shouldCanNotUndoIfEmptyDocument() {
        assertFalse(mHtmlDocument.canUndo())
    }

    @Test
    fun shouldCanNotUndoIfUndoAllActions() {
        mHtmlDocument.setTitle("Title1")
        mHtmlDocument.setTitle("Title2")
        mHtmlDocument.setTitle("Title3")
        mHtmlDocument.undo()
        mHtmlDocument.undo()
        mHtmlDocument.undo()
        assertFalse(mHtmlDocument.canUndo())
    }

    @Test
    fun shouldUndoSetTitle() {
        val title = "Title"
        mHtmlDocument.setTitle(title)
        mHtmlDocument.setTitle("Another title")
        mHtmlDocument.undo()
        assertEquals(title, mHtmlDocument.getTitle())
    }

    @Test
    fun shouldUndoInsertParagraph() {
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.undo()
        assertEquals(0, mHtmlDocument.getItemCount())
    }

    @Test
    fun shouldUndoInsertImage() {
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        mHtmlDocument.undo()
        assertEquals(0, mHtmlDocument.getItemCount())
    }

    @Test
    fun shouldUndoRedidAction() {
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 1)
        mHtmlDocument.undo()
        mHtmlDocument.redo()
        mHtmlDocument.undo()
        assertEquals(1, mHtmlDocument.getItemCount())
    }

    @Test
    fun shouldCanRedo() {
        mHtmlDocument.setTitle("title")
        mHtmlDocument.undo()
        assert(mHtmlDocument.canRedo())
    }

    @Test
    fun shouldCanNotRedo() {
        mHtmlDocument.setTitle("title")
        assertFalse(mHtmlDocument.canRedo())
    }

    @Test
    fun shouldRedoUndidTitle() {
        val title = "Title"
        mHtmlDocument.setTitle(title)
        mHtmlDocument.undo()
        mHtmlDocument.redo()
        assertEquals(title, mHtmlDocument.getTitle())
    }

    @Test
    fun shouldRedoUndidParagraph() {
        val paragraph = mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.undo()
        mHtmlDocument.redo()
        assertEquals(mHtmlDocument.getItem(0).getParagraph(), paragraph)
    }

    @Test
    fun shouldRedoUndidImage() {
        val image = mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        mHtmlDocument.undo()
        mHtmlDocument.redo()
        assertEquals(mHtmlDocument.getItem(0).getImage(), image)
    }

    @Test
    fun shouldCreateDirWhenSaveDocument() {
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        mHtmlDocument.setTitle("Title")
        val path = "temp"
        mHtmlDocument.save(path)
        val dir = File(path)
        assert(dir.exists())
        deleteTempDir(dir)
    }

    @Test
    fun shouldHaveHtmlDocumentAfterSave() {
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        val title = "Title"
        mHtmlDocument.setTitle(title)
        val path = "temp"
        mHtmlDocument.save(path)
        val dir = File(path)
        assert(dir.list().contains("$title.html"))
        deleteTempDir(dir)
    }

    @Test
    fun shouldHaveImageDirectoryAfterSave() {
        mHtmlDocument.insertParagraph("text", 0)
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        val title = "Title"
        mHtmlDocument.setTitle(title)
        val path = "temp"
        mHtmlDocument.save(path)
        val dir = File(path)
        assert(dir.list().contains("image"))
        deleteTempDir(dir)
    }

    @Test
    fun shouldDeleteTempDirectoryAfterClosing() {
        mHtmlDocument.insertImages("src/main/resources/hypa.jpg", 300, 200, 0)
        mHtmlDocument.close()
        val tempDir = File("image/")
        assertFalse(tempDir.exists())
    }

    private fun deleteTempDir(dir: File) {
        dir.listFiles { currentDir, name ->
            val file = File("${currentDir.path}/$name")
            if (file.isDirectory) {
                deleteTempDir(file)
            }
            file.delete()
            return@listFiles true
        }
    }

    @After
    fun recreateDocument() {
        mHtmlDocument.close()
        mHtmlDocument = HTMLDocument()
    }

}