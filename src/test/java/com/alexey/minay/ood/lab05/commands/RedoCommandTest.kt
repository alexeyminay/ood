package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class RedoCommandTest {

    private val mDocument = mock<IDocument>()
    private val mRedoCommand = RedoCommand(mDocument)

    @Test
    fun shouldRedoLastAction() {
        mRedoCommand.execute()
        verify(mDocument).redo()
    }

}