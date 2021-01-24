package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.alexey.minay.ood.lab05.document.Paragraph
import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class ReplaceTextCommandTest {

    private val mDocumentItems = mutableListOf<IDocumentItem>()

    @Test
    fun shouldReplaceText() {
        val text = "text"
        val position = 0
        val paragraph = Paragraph("")
        val item = DocumentItem(paragraph = paragraph)
        mDocumentItems.add(item)
        ReplaceTextCommand(mDocumentItems, text, position).execute()
        assertEquals(text, mDocumentItems[position].getParagraph()?.getText())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfIncorrectPosition() {
        val text = "text"
        val position = 1
        val paragraph = Paragraph("")
        val item = DocumentItem(paragraph = paragraph)
        mDocumentItems.add(item)
        ReplaceTextCommand(mDocumentItems, text, position).execute()
        assertEquals(text, mDocumentItems[position].getParagraph()?.getText())
    }

    @Test
    fun shouldUnExecuteChanges() {
        val text = "text"
        val position = 0
        val paragraph = Paragraph("")
        val item = DocumentItem(paragraph = paragraph)
        mDocumentItems.add(item)
        ReplaceTextCommand(mDocumentItems, text, position).execute()
        assertEquals(text, mDocumentItems[position].getParagraph()?.getText())
    }

    @After
    fun clear() {
        mDocumentItems.clear()
    }

}