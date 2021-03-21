package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.common.IDocumentItem
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class InsertParagraphCommandTest {

    private val mDocumentItems = mutableListOf<IDocumentItem>()

    @Test
    fun shouldInsertParagraphWithText() {
        val position = 0
        val text = "text"
        val insertParagraphCommand = InsertParagraphCommand(mDocumentItems, text, position)
        insertParagraphCommand.execute()
        assertEquals(text, mDocumentItems[0].getParagraph()?.getText())
    }

    @Test
    fun shouldInsertParagraphAtTheSpecifiedPosition() {
        val position = 0
        val text1 = "text1"
        val text2 = "text2"
        InsertParagraphCommand(mDocumentItems, text1, position).execute()
        InsertParagraphCommand(mDocumentItems, text2, position).execute()
        assertEquals(text2, mDocumentItems[0].getParagraph()?.getText())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfIncorrectPosition() {
        val position = 1
        val text = "text"
        InsertParagraphCommand(mDocumentItems, text, position).execute()
    }

    @Test
    fun shouldUnExecuteChanges() {
        val position = 0
        val text = "text"
        val command = InsertParagraphCommand(mDocumentItems, text, position)
        command.execute()
        assertEquals(1, mDocumentItems.size)
        command.unExecute()
        assertEquals(0, mDocumentItems.size)
    }

    @After
    fun clear() {
        mDocumentItems.clear()
    }

}