package com.alexey.minay.ood.lab09.ui.view

import com.alexey.minay.ood.lab09.PresenterFactory
import com.alexey.minay.ood.lab09.application.usecases.ChangeFrameState
import com.alexey.minay.ood.lab09.ui.MVP
import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.stage.Stage

class SimpleCanvasView: MVP.ICanvasView {

    private var mCanvasPresenter: MVP.ICanvasPresenter
    private var mCanvas: Canvas

    init {
        val layout = StackPane()
        val stage = Stage()
        mCanvas = Canvas(800.0, 450.0)
        val graphicsContext = mCanvas.graphicsContext2D
        layout.children.add(mCanvas)
        val scene = Scene(layout, 800.0, 450.0)
        stage.isResizable = false
        stage.scene = scene
        stage.show()
        mCanvasPresenter = PresenterFactory.createNewAppStateCanvasPresenterFor(this, graphicsContext)
        mCanvas.onMousePressed = EventHandler {
            mCanvasPresenter.onMousePressed(it.x, it.y, it.isControlDown)
        }
        mCanvas.onMouseDragged = EventHandler {
            mCanvasPresenter.onMouseDragged(it.x, it.y, mCanvas.width, mCanvas.height)
        }
        mCanvas.onMouseReleased = EventHandler {
            mCanvasPresenter.onMouseReleased(it.x, it.y)
        }
        mCanvas.onMouseMoved = EventHandler {
            mCanvasPresenter.onMouseMoved(it.x, it.y)
        }
    }

    override fun updateCursor(cursorState: ChangeFrameState) {
        mCanvas.scene.cursor = when (cursorState) {
            ChangeFrameState.NOT_RESIZE -> Cursor.DEFAULT
            ChangeFrameState.RIGHT_BOTTOM_RESIZE -> Cursor.NW_RESIZE
            ChangeFrameState.LEFT_TOP_RESIZE -> Cursor.NW_RESIZE
            ChangeFrameState.LEFT_BOTTOM_RESIZE -> Cursor.NE_RESIZE
            ChangeFrameState.RIGHT_TOP_RESIZE -> Cursor.NE_RESIZE
        }
    }

}