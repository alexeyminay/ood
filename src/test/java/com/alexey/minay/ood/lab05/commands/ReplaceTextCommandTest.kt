//package com.alexey.minay.ood.lab05.commands
//
//import com.alexey.minay.ood.lab05.document.IDocument
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Test
//
//class ReplaceTextCommandTest {
//
//    private val mDocument = mock<IDocument>()
//    private val mText = "text"
//    private lateinit var mReplaceTextCommand: ReplaceTextCommand
//
//    @Test
//    fun shouldReplaceTextAtTheLastPosition() {
//        mReplaceTextCommand = ReplaceTextCommand(mDocument, mText)
//        mReplaceTextCommand.execute()
//        verify(mDocument).getDocumentSize()
//        verify(mDocument).replaceTextInParagraph(mText, mDocument.getDocumentSize())
//    }
//
//    @Test
//    fun shouldReplaceTextAtTheSpecifiedPosition() {
//        val position = 1
//        mReplaceTextCommand = ReplaceTextCommand(mDocument, mText, position)
//        mReplaceTextCommand.execute()
//        verify(mDocument).replaceTextInParagraph(mText, position)
//    }
//
//}