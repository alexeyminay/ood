package com.alexey.minay.ood.lab05.document

import com.alexey.minay.ood.lab05.FileHelper
import com.alexey.minay.ood.lab05.History
import com.alexey.minay.ood.lab05.commands.*

class Document(
        private val history: History,
        private val fileHelper: FileHelper
) : IDocument {

    private var mTitle: Title = Title("")
    private var mDocument = mutableListOf<IDocumentItem>()

    override fun insertParagraph(text: String, position: Int?) {
        history.addAnExecute(InsertParagraphCommand(mDocument, text, position ?: mDocument.size))
    }

    override fun replaceTextParagraph(text: String, position: Int?) {
        history.addAnExecute(ReplaceTextCommand(mDocument, text, position ?: mDocument.size))
    }

    override fun insertImage(path: String, height: Int, width: Int, position: Int?) {
        history.addAnExecute(
                InsertImageCommand(
                        documentItems = mDocument,
                        path = path,
                        width = width,
                        height = height,
                        position = position ?: mDocument.size,
                        fileHelper = fileHelper)
        )
    }

    override fun resizeImage(width: Int, height: Int, position: Int?) {
        history.addAnExecute(
                ResizeImageCommand(
                        documentItems = mDocument,
                        width = width,
                        height = height,
                        position = position ?: mDocument.size
                )
        )
    }

    override fun getItem(index: Int): IDocumentItem {
        if (index > mDocument.size) throw RuntimeException("Position $index doesn't exist")
        return mDocument[index]
    }

    override fun deleteItem(index: Int) {
        if (index > mDocument.size) throw RuntimeException("Position $index doesn't exist")
        mDocument.removeAt(index)
    }

    override fun getItems() = mDocument

    override fun getItemCount() = mDocument.size

    override fun getTitle() = mTitle.data

    override fun setTitle(title: String) {
        history.addAnExecute(SetTitleCommand(mTitle, title))
    }

    override fun undo() {
        history.undo()
    }

    override fun redo() {
        history.redo()
    }

    override fun getDocumentSize() = mDocument.size

    override fun close() {
        history.clearHistory()
    }

}