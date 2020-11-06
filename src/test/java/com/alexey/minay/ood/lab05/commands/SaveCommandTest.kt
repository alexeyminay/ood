package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class SaveCommandTest {

    private val mDocument = mock<IDocument>()
    private val mPath = "test/path.txt"
    private val mSaveCommand: SaveCommand = SaveCommand(mDocument, mPath)

    @Test
    fun shouldSaveDocument() {
        mSaveCommand.execute()
        verify(mDocument).save(mPath)
    }

}