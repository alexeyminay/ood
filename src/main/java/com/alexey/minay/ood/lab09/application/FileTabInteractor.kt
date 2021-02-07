package com.alexey.minay.ood.lab09.application

import java.io.File

class FileTabInteractor(
    private val fileHelper: IFileHelper,
    private val documentAdapter: DocumentAdapter
) {

    fun saveIn(file: File) {
        fileHelper.saveFile(file, documentAdapter.getAllState())
    }

    fun saveAsIn(file: File) {
        fileHelper.saveFile(file, documentAdapter.getAllState())
    }

    fun open(file: File) {
        fileHelper.openFile(file, documentAdapter::setShape)
    }

}