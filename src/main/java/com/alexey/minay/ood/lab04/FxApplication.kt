package com.alexey.minay.ood.lab04

import com.alexey.minay.ood.lab04.shapes.ShapeFactory
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.VBox
import javafx.stage.Stage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FxApplication : Application() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(FxApplication::class.java)
        }
    }

    override fun start(primaryStage: Stage?) {
        val root = VBox()
        val canvas = Canvas(800.0, 600.0)
        val graphicsContext = canvas.graphicsContext2D
        root.children.add(canvas)
        val scene = Scene(root)
        primaryStage?.scene = scene
        primaryStage?.setOnShown {
            GlobalScope.launch {
                printHelp()
                val factory = ShapeFactory()
                val designer = Designer(factory)
                val painter = Painter()
                val canvasFx = CanvasFx(graphicsContext)
                while (true) {
                    try {
                        val input = readLine() ?: return@launch
                        val draft = designer.createDraft(input.byteInputStream())
                        painter.drawPicture(draft, canvasFx)
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            }

        }
        primaryStage?.show()
    }

    fun printHelp() {
        println("Введите фигуры для рисования, после ввода нажмите \"enter\"")
        println("""
                    Пример:
                    rectangle green 40 50 70 80
                    triangle red 40 50 80 90 40 120
                    ellipse blue 120 140 100 80
                    regularPolygon yellow 17 150 160 70
                """.trimIndent())
    }

}