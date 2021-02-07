package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.domain.shapes.Shape
import java.io.File

interface IFileHelper {
    fun openFile(file: File, onOpened: (List<Shape>) -> Unit)
    fun saveFile(file: File, imageState: List<Shape>)
}