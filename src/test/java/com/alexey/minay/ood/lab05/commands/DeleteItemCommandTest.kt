package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.alexey.minay.ood.lab05.document.IDocumentItem
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class DeleteItemCommandTest {

    private val mDocumentItem = mock<IDocumentItem>()
    private lateinit var mDeleteItemCommand: DeleteItemCommand

    @Test
    fun shouldRedoLastAction() {
        val position = 0
        val documentItems = mutableListOf(mDocumentItem)
        mDeleteItemCommand = DeleteItemCommand(documentItems, position)
        mDeleteItemCommand.execute()
        assert(documentItems.isEmpty())
    }

}