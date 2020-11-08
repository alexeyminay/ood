package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class DeleteItemCommandTest {

    private val mDocument = mock<IDocument>()
    private val mPosition = 0
    private val mDeleteItemCommand = DeleteItemCommand(mDocument, mPosition)

    @Test
    fun shouldRedoLastAction() {
        mDeleteItemCommand.execute()
        verify(mDocument).deleteItem(mPosition)
    }

}