package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.common.IDocumentItem
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class DeleteItemCommandTest {

    private val mDocumentItem = mock<IDocumentItem>()
    private lateinit var mDeleteItemCommand: DeleteItemCommand
    private val mFileHelper = mock<FileHelper>()

    @Test
    fun shouldDeleteItem() {
        val position = 0
        val documentItems = mutableListOf(mDocumentItem)
        mDeleteItemCommand = DeleteItemCommand(documentItems, position, mFileHelper)
        mDeleteItemCommand.execute()
        assert(documentItems.isEmpty())
    }

    @Test
    fun shouldNotDeleteItem() {
        val position = 0
        val documentItems = mutableListOf(mDocumentItem)
        mDeleteItemCommand = DeleteItemCommand(documentItems, position, mFileHelper)
        mDeleteItemCommand.execute()
        mDeleteItemCommand.unExecute()
        assertEquals(mDocumentItem, documentItems[0])
    }

}