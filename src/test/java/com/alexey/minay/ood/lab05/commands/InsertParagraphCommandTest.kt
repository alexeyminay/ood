//package com.alexey.minay.ood.lab05.commands
//
//import com.alexey.minay.ood.lab05.document.IDocument
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Test
//
//class InsertParagraphCommandTest {
//
//    private val mDocument = mock<IDocument>()
//    private val mText = "text"
//    private lateinit var mInsertParagraphCommand: InsertParagraphCommand
//
//    @Test
//    fun shouldInsertParagraphAtTheLastPosition() {
//        mInsertParagraphCommand = InsertParagraphCommand(mDocument, mText)
//        mInsertParagraphCommand.execute()
//        verify(mDocument).getDocumentSize()
//        verify(mDocument).insertParagraph(mText, mDocument.getDocumentSize())
//    }
//
//    @Test
//    fun shouldInsertParagraphAtTheSpecifiedPosition() {
//        val position = 1
//        mInsertParagraphCommand = InsertParagraphCommand(mDocument, mText, position)
//        mInsertParagraphCommand.execute()
//        verify(mDocument).insertParagraph(mText, position)
//    }
//
//}