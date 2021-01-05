package com.alexey.minay.ood.lab05.document

import java.io.File
import java.util.*

class Document : IDocument {

    private var mTitle: String = ""
    private var mDocument = mutableListOf<IDocumentItem>()

    private val mState = ArrayDeque<DocumentState>()
    private val mCanceledState = ArrayDeque<DocumentState>()
    private val mDeletableImages = mutableMapOf<String, Int>()

    override fun insertParagraph(text: String, position: Int): IParagraph {
        if (position > mDocument.size) throw RuntimeException("$position is incorrectPosition")
        val paragraph = Paragraph(text)
        val documentItem = DocumentItem(paragraph = paragraph)
        deleteOldState()
        saveState()
        mDocument.add(position, documentItem)
        return paragraph
    }

    override fun insertImages(path: String, height: Int, width: Int, position: Int): IImage {
        if (position > mDocument.size) throw RuntimeException("$position is incorrectPosition")
        val image = Image("$IMAGE_PREFIX${System.currentTimeMillis()}", IMAGE_PATH, width, height)
        writeFile(path, image.getPath(), image.getPath() + image.getName())
        mDeletableImages[image.getName()] = 0
        val documentItem = DocumentItem(image = image)
        deleteOldState()
        saveState()
        mDocument.add(position, documentItem)
        return image
    }

    override fun getItem(index: Int): IDocumentItem {
        if (index > mDocument.size) throw RuntimeException("Position $index doesn't exist")
        return mDocument[index]
    }

    override fun deleteItem(index: Int) {
        if (index > mDocument.size) throw RuntimeException("Position $index doesn't exist")
        deleteOldState()
        saveState()
        mDocument.removeAt(index)
    }

    override fun getItems() = mDocument

    override fun getItemCount() = mDocument.size

    override fun getTitle() = mTitle

    override fun setTitle(title: String) {
        deleteOldState()
        saveState()
        mTitle = title
    }

    override fun canUndo() = mState.isNotEmpty()

    override fun undo() {
        if (!canUndo()) return
        val document = mutableListOf<IDocumentItem>()
        document.addAll(mDocument)
        val canceledState = DocumentState(document, mTitle)
        mCanceledState.add(canceledState)
        canceledState.document.forEach {
            val imageName = it.getImage()?.getName()
            if (imageName != null && mDeletableImages[imageName] != null) {
                mDeletableImages[imageName] = mDeletableImages[imageName]!! - 1
            }
        }
        revertState()
    }

    override fun canRedo() = mCanceledState.isNotEmpty()

    override fun redo() {
        if (!canRedo()) return
        val document = mutableListOf<IDocumentItem>()
        document.addAll(mDocument)
        mState.add(DocumentState(document, mTitle))
        val revertedState = mCanceledState.pollLast()
        mState.add(revertedState)
        revertedState.document.forEach {
            val imageName = it.getImage()?.getName()
            if (imageName != null && mDeletableImages[imageName] != null) {
                mDeletableImages[imageName] = mDeletableImages[imageName]!! + 1
            }
        }
        revertState()
    }

    override fun getDocumentSize() = mDocument.size

    override fun close() {
        val dir = File(IMAGE_PATH)
        if (dir.isDirectory) {
            val files = dir.list()
            files.forEach {
                File(IMAGE_PATH + it).delete()
            }
            dir.delete()
        }
        mDocument = mutableListOf()
        mTitle = ""
    }

    private fun saveState() {
        if (mState.size == MAX_SAVED_STATE) {
            val deletingState = mState.pollFirst()
            deletingState.document.forEach {
                val imageDeleteStateValue = mDeletableImages[it.getImage()?.getName()]
                if (imageDeleteStateValue != null && imageDeleteStateValue > MAX_SAVED_STATE) {
                    mDeletableImages.remove(it.getImage()?.getName())
                }
            }
        }
        val document = mutableListOf<IDocumentItem>()
        document.addAll(mDocument)
        mState.add(DocumentState(document, mTitle))
        mCanceledState.clear()
        mDeletableImages.forEach { (imageName, savesStateCount) -> mDeletableImages[imageName] = savesStateCount + 1 }
    }

    private fun deleteOldState() {
        mCanceledState.forEach { state ->
            state.document.forEach { item ->
                val imageName = item.getImage()?.getName()
                if (item.getImage() != null &&
                        item.getImage()?.getPath() == IMAGE_PATH &&
                        mDeletableImages[imageName] == 0) {
                    File(item.getImage()?.let { it.getPath() + it.getName() } ?: "").delete()
                    mDeletableImages.remove(imageName)
                }
            }
        }
        mCanceledState.clear()
    }

    private fun revertState() {
        val state = mState.pollLast() ?: return
        mTitle = state.title
        mDocument.clear()
        mDocument.addAll(state.document)
    }

    private fun writeFile(donorFilePath: String, newPath: String, newFilePath: String) {
        val file = File(donorFilePath)
        val tempPathDir = File(newPath)
        if (!tempPathDir.exists()) tempPathDir.mkdir()
        val tempFile = File(newFilePath)
        tempFile.writeBytes(file.readBytes())
    }

    companion object {

        private const val IMAGE_PATH = "image/"
        private const val IMAGE_PREFIX = "img_"
        private const val MAX_SAVED_STATE = 10

    }

}