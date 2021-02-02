package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.IShape
import java.io.File

interface IFileHelper {
    fun openFile(file: File, onOpened: (List<IShape>) -> Unit)
    fun saveFile(file: File, imageState: List<IShape>)
}