package com.alexey.minay.ood.lab09.application

import java.io.File

interface IFileHelper {
    fun openFile(file: File, onOpened: (List<IAppShape>) -> Unit)
    fun saveFile(file: File, imageState: List<IAppShape>)
}