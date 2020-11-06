package com.alexey.minay.ood.lab05.document

import org.junit.After
import org.junit.Test
import kotlin.test.assertEquals

class HTMLDocumentTest {

    private var mHtmlDocument = HTMLDocument()

    @Test
    fun shouldInsertParagraph() {
        val paragraph = mHtmlDocument.insertParagraph("text", 0)
        assertEquals(paragraph, mHtmlDocument.getItem(0).getParagraph())
    }

    @After
    fun clean() {
        mHtmlDocument = HTMLDocument()
    }
}