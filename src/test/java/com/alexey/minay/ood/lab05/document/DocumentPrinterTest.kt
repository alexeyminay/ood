package com.alexey.minay.ood.lab05.document

import com.alexey.minay.ood.lab05.DocumentPrinter
import com.alexey.minay.ood.lab05.common.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.mockito.Mockito
import kotlin.test.assertEquals

class DocumentPrinterTest {

    private val mDocument = mock<IDocument>()
    private var mPrintedText: String = ""
    private val mDocumentPrinter = DocumentPrinter(mDocument, ::concatPrintedText)

    @Test
    fun shouldInvokeConcatPrintText() {
        val items = mutableListOf(
                DocumentItem(
                        image = Image("name", "path", 200, 300)
                ),
                DocumentItem(
                        paragraph = Paragraph("text")
                )
        )
        Mockito.`when`(mDocument.getItems()).thenReturn(items)
        Mockito.`when`(mDocument.getTitle()).thenReturn("Title")
        mDocumentPrinter.print()
        verify(mDocument).getTitle()
        verify(mDocument).getItems()
        assertEquals("Title\n" +
                "0: Image 200 300 pathname null\n" +
                "1: null Paragraph text\n", mPrintedText)
    }

    private fun concatPrintedText(text: String) {
        mPrintedText += text
        mPrintedText += "\n"
    }

}