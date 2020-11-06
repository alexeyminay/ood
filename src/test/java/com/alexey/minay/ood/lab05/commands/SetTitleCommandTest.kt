package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class SetTitleCommandTest {

    private val mDocument = mock<IDocument>()
    private val mTitle = "test"
    private val mSetTitleCommand: SetTitleCommand = SetTitleCommand(mDocument, mTitle)

    @Test
    fun shouldSetTitle() {
        mSetTitleCommand.execute()
        verify(mDocument).setTitle(mTitle)
    }

}