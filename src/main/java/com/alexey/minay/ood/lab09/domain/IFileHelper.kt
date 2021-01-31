package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.stateHandler.IShape
import java.io.File

interface IFileHelper {
    fun openFile(file: File, onOpened: (List<IShape>) -> Unit)
    fun saveFile(file: File, imageState: List<IShape>)
}