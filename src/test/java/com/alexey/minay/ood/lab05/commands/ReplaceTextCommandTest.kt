package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.DocumentItem
import com.alexey.minay.ood.lab05.document.Paragraph
import org.junit.Test
import kotlin.test.assertEquals

class ReplaceTextCommandTest {


    @Test
    fun shouldReplaceText() {
        val paragraph = Paragraph("")
        val item = DocumentItem(paragraph = paragraph)
        val text = "text"
        ReplaceTextCommand(item, text).execute()
        assertEquals(text, item.getParagraph()?.getText())
    }

    @Test(expected = RuntimeException::class)
    fun shouldThrowExceptionIfDoNotHaveParagraph() {
        val text = "text"
        val item = DocumentItem()
        ReplaceTextCommand(item, text).execute()
    }

    @Test
    fun shouldUnExecuteChanges() {
        val text1 = "text1"
        val text2 = "text2"
        val paragraph = Paragraph(text1)
        val item = DocumentItem(paragraph = paragraph)
        val command = ReplaceTextCommand(item, text2).apply { execute() }
        command.unExecute()
        assertEquals(text1, item.getParagraph()?.getText())
    }

    @Test
    fun shouldExecuteAfterUnExecute() {
        val text1 = "text1"
        val text2 = "text2"
        val paragraph = Paragraph(text1)
        val item = DocumentItem(paragraph = paragraph)
        val command = ReplaceTextCommand(item, text2).apply { execute() }
        command.unExecute()
        command.execute()
        assertEquals(text2, item.getParagraph()?.getText())
    }

}