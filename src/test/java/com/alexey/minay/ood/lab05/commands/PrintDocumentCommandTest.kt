//package com.alexey.minay.ood.lab05.commands
//
//import com.alexey.minay.ood.lab05.document.DocumentPrinter
//import com.nhaarman.mockitokotlin2.mock
//import com.nhaarman.mockitokotlin2.verify
//import org.junit.Test
//
//class PrintDocumentCommandTest {
//
//    private val mDocumentPrinter = mock<DocumentPrinter>()
//    private val mPrintDocumentCommand = PrintDocumentCommand(mDocumentPrinter)
//
//    @Test
//    fun shouldPrintDocument() {
//        mPrintDocumentCommand.execute()
//        verify(mDocumentPrinter).print()
//    }
//
//}