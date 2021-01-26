package com.alexey.minay.ood.lab07.ui

import com.alexey.minay.ood.lab07.domain.Slide
import com.alexey.minay.ood.lab07.FxCanvas
import com.alexey.minay.ood.lab07.domain.ICanvas
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.stage.Stage

class SlideScene(
        private val primaryStage: Stage?,
        private val slidePresenter: MVP.ISlidePresenter
) : MVP.ISlideSceneView {

    private var mCanvas: ICanvas? = null

    fun onStart() {
        val root = HBox()
        initControlPane(root)
        initCanvas(root)
        primaryStage?.scene = Scene(root, SCENE_WIDTH, SCENE_HEIGHT)
        primaryStage?.show()
        slidePresenter.setScene(this)
    }

    override fun showSlide(slide: Slide) {
        mCanvas?.clearRect(0.0, 0.0, CANVAS_WIDTH, CANVAS_WIDTH)
        slide.forEach { shape ->
            mCanvas?.let { shape.draw(it) }
        }
    }

    private fun initControlPane(root: HBox) {
        val controlPane = VBox()
        controlPane.addNextButton()
        controlPane.addPreviousButton()
        setControlPaneParams(controlPane)
        root.children.add(controlPane)
    }

    private fun VBox.addNextButton() {
        val next = Button("next slide")
        next.setOnMouseClicked {
            slidePresenter.onGetNextSlide()
        }
        children.add(next)
    }

    private fun VBox.addPreviousButton() {
        val previous = Button("previous slide")
        previous.setOnMouseClicked {
            slidePresenter.onGetPreviousSlide()
        }
        children.add(previous)
    }

    private fun setControlPaneParams(controlPane: VBox) {
        controlPane.padding = Insets(CONTROL_PANE_INSET, CONTROL_PANE_INSET, CONTROL_PANE_INSET, CONTROL_PANE_INSET)
        controlPane.background = Background(BackgroundFill(Color.web(CONTROL_PANE_BACKGROUND_COLOR), CornerRadii.EMPTY, Insets.EMPTY))
    }

    private fun initCanvas(root: HBox) {
        val canvas = Canvas(CANVAS_WIDTH, CANVAS_HEIGHT)
        val graphicsContext = canvas.graphicsContext2D
        mCanvas = FxCanvas(graphicsContext)
        root.children.add(canvas)
    }

    companion object {

        private const val CONTROL_PANE_INSET = 20.0
        private const val CONTROL_PANE_BACKGROUND_COLOR = "#34495e"
        private const val CANVAS_HEIGHT = 600.0
        private const val CANVAS_WIDTH = 800.0
        private const val SCENE_HEIGHT = 600.0
        private const val SCENE_WIDTH = 930.0

    }

}