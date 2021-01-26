package com.alexey.minay.ood.lab05.document

interface IDocument {
    fun insertParagraph(text: String, position: Int?)
    fun replaceTextParagraph(text: String, position: Int?)
    fun insertImage(path: String, height: Int, width: Int, position: Int?)
    fun resizeImage(width: Int, height: Int, position: Int?)
    fun getItem(index: Int): IDocumentItem
    fun getItems(): List<IDocumentItem>
    fun deleteItem(index: Int)
    fun getItemCount(): Int
    fun getTitle(): String
    fun setTitle(title: String)
    fun undo()
    fun redo()
    fun getDocumentSize(): Int
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