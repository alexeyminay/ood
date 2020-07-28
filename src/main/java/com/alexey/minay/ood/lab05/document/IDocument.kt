package com.alexey.minay.ood.lab05.document

interface IDocument {
    fun insertParagraph(text: String, position: Int): IParagraph
    fun replaceTextInParagraph(text: String, position: Int): IParagraph
    fun insertImages(path: String, height: Int, width: Int, position: Int): IImage
    fun resizeImage(height: Int, width: Int, position: Int): IImage
    fun getItem(index: Int): IDocumentItem
    fun getItems(): List<IDocumentItem>
    fun deleteItem(index: Int)
    fun getItemCount(): Int
    fun getTitle(): String
    fun setTitle(title: String)
    fun canUndo(): Boolean
    fun undo()
    fun canRedo(): Boolean
    fun redo()
    fun save(path: String)
    fun getLastPosition(): Int
    fun close()
}

interface IParagraph {
    fun getText(): String
    fun setText(text: String)
}

interface IImage {
    fun getName(): String
    fun getPath(): String
    fun getHeight(): Int
    fun getWidth(): Int
    fun resize(width: Int, height: Int)
}

interface IDocumentItem {
    fun getImage(): IImage?
    fun getParagraph(): IParagraph?
}

interface IDocumentPrinter {
    fun print()
}