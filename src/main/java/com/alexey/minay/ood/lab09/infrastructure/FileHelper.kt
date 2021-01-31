package com.alexey.minay.ood.lab09.infrastructure

import com.alexey.minay.ood.lab09.domain.IFileHelper
import com.alexey.minay.ood.lab09.domain.IShape
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class FileHelper : IFileHelper {

    override fun openFile(file: File, onOpened: (List<IShape>) -> Unit) {
        GlobalScope.launch {
            val shapes = withContext(Dispatchers.IO) {
                val json = file.readText()
                Gson().fromJson(json, ShapesJson::class.java)
            }
            withContext(Dispatchers.JavaFx) {
                onOpened(shapes.toDomainData())
            }
        }
    }

    override fun saveFile(file: File, imageState: List<IShape>) {
        GlobalScope.launch {
            withContext(Dispatchers.IO) {
                val shapesJson = ShapesJson(imageState.map { it.toJsonData() })
                val json = Gson().toJson(shapesJson)
                val jsonFile = File(file.absoluteFile.toString() + ".json")
                jsonFile.writeText(json.toString())
            }
        }
    }

}