package com.alexey.minay.ood.lab09

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import java.io.File

class CanvasApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(CanvasApplication::class.java)
        }
    }

    override fun start(primaryStage: Stage?) {
        val url = File("src/main/java/com/alexey/minay/ood/lab09/main.fxml").toURI().toURL()
        val root = FXMLLoader.load<Parent>(url)
        val scene = Scene(root)
        primaryStage?.scene = scene
        primaryStage?.title = "Lab09"
        primaryStage?.width = 800.0
        primaryStage?.height = 600.0
        primaryStage?.isResizable = false
        primaryStage?.show()
    }

}