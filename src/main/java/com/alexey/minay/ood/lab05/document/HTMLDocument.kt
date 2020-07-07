package com.alexey.minay.ood.lab05.document

import com.alexey.minay.ood.lab05.dsl.Html
import java.io.File
import java.util.*

class HTMLDocument : IDocument {

    private var mTitle: String = ""
    private var mDocument = mutableListOf<IDocumentItem>()
    private val mState = ArrayDeque<DocumentState>()
    private val mCanceledState = ArrayDeque<DocumentState>()

    override fun insertParagraph(text: String, position: Int?): IParagraph {
        val insertPosition = position ?: mDocument.size
        val paragraph = Paragraph(text)
        val documentItem = DocumentItem(paragraph = paragraph)
        mDocument.add(insertPosition, documentItem)
        saveState()
        return paragraph
    }

    override fun insertImages(path: String, height: Int, width: Int, position: Int?): IImage {
        val insertPosition = position ?: mDocument.size
        val image = Image(path, width, height)
        val documentItem = DocumentItem(image = image)
        mDocument.add(insertPosition, documentItem)
        saveState()
        return image
    }

    override fun getItem(index: Int) = mDocument[index]

    override fun deleteItem(index: Int) {
        mDocument.removeAt(index)
    }

    override fun getItemCount() = mDocument.size

    override fun getTitle() = mTitle

    override fun setTitle(title: String) {
        mTitle = title
        saveState()
    }

    override fun canUndo() = mState.isNotEmpty()

    override fun undo() {
        if (!canUndo()) return
        mCanceledState.push(mState.pollLast())
        undoState()
    }

    override fun canRedo() = mCanceledState.isNotEmpty()

    override fun redo() {
        if (!canRedo()) return
        mCanceledState.push(mState.pollLast())
        undoState()
    }

    override fun save(path: String) {
        val fileName = when {
            mTitle.isBlank() -> "new_document"
            else -> mTitle
        }
        val file = File("$path$fileName.html")
        file.writeText(getHtml())
    }

    private fun saveState() {
        if (mState.size == 10) mState.pollFirst()
        val document = mutableListOf<IDocumentItem>()
        document.addAll(mDocument)
        mState.add(DocumentState(document, mTitle))
        mCanceledState.clear()
    }

    private fun undoState() {
        val state = mState.lastOrNull() ?: return
        mTitle = state.title
        mDocument.clear()
        mDocument.addAll(state.document)
    }

    private fun getHtml() =
            Html {
                head(mTitle)
                body {
                    title(mTitle)
                    mDocument.forEach {
                        paragraph(it.getParagraph())
                        image(it.getImage())
                    }
                }
            }

}
