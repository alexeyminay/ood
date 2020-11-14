package com.alexey.minay.ood.lab09.domain

import java.io.File

interface IFileRepository {
    fun openFile(file: File)
    fun saveFile(file: File)
}