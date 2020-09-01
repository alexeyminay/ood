package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Test

class CloseDocumentCommandTest {

    private val mDocument = mock<IDocument>()
    private val mCloseCommand = CloseDocumentCommand(mDocument)

    @Test
    fun shouldCloseDocument() {
        mCloseCommand.execute()
        verify(mDocument).close()
    }

}