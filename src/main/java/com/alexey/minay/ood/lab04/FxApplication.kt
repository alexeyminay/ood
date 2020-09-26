package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.ShapeFactory
import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.stage.Stage

class FxApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(FxApplication::class.java)
        }
    }

    override fun start(primaryStage: Stage?) {
        val root = Group()
        val canvas = Canvas(800.0, 600.0)
        val graphicsContext = canvas.graphicsContext2D

        //val inputStream = File("composite.txt").inputStream()
        val factory = ShapeFactory()
        val designer = Designer(factory)
        val painter = Painter()
        val canvasFx = CanvasFx(graphicsContext)
        root.children.add(canvas)
        val scene = Scene(root)
        primaryStage?.scene = scene

        primaryStage?.show()

    }


}