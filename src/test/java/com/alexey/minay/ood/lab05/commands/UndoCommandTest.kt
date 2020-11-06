package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class UndoCommandTest {

    private val mDocument = mock<IDocument>()
    private val mUndoCommand = UndoCommand(mDocument)

    @Test
    fun shouldRedoLastAction() {
        mUndoCommand.execute()
        verify(mDocument).undo()
    }

}