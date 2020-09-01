package com.alexey.minay.ood.lab05.commands

import com.alexey.minay.ood.lab05.document.IDocument
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class InsertImageCommandTest {

    private val mDocument = mock<IDocument>()

    @Test
    fun shouldInsertImage() {
        val path = "path"
        val width = 300
        val height = 200
        val position = 1
        val insertCommand = InsertImageCommand(
                document = mDocument,
                path = path,
                width = width,
                height = height,
                position = position
        )
        insertCommand.execute()
        verify(mDocument).insertImages(
                path = path,
                width = width,
                height = height,
                position = position
        )
    }

}