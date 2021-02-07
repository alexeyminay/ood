package com.alexey.minay.ood.lab09.ui.view

import com.alexey.minay.ood.lab09.PresenterFactory
import com.alexey.minay.ood.lab09.application.usecases.ChangeFrameState
import com.alexey.minay.ood.lab09.ui.MVP
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Cursor
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.ColorPicker
import javafx.scene.control.TabPane
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import javafx.stage.Stage
import java.net.URL
import java.util.*

class CanvasView : MVP.ICanvasView, MVP.IFileTabView, Initializable {

    @FXML
    private lateinit var mCanvas: Canvas
    val graphicsContext: GraphicsContext
        get() = mCanvas.graphicsContext2D

    @FXML
    private lateinit var mStrokePicker: ColorPicker

    @FXML
    private lateinit var mFillPicker: ColorPicker

    @FXML
    private lateinit var mTabPane: TabPane

    private lateinit var mCanvasPresenter: MVP.ICanvasPresenter
    private lateinit var mFilePresenter: MVP.IFileTabPresenter
    private lateinit var mHomeTabPresenter: MVP.IHomeTabPresenter
    private val mFileChooser by lazy {
        FileChooser().apply {
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        mCanvasPresenter = PresenterFactory.createCanvasPresenterFor(this)
        mFilePresenter = PresenterFactory.createFilePresenterFor(this)
        mHomeTabPresenter = PresenterFactory.createHomeTabPresenter(this)
        mStrokePicker.value = Color.CADETBLUE
        mFillPicker.value = Color.BISQUE
        mTabPane.selectionModel.selectLast()
    }

    @FXML
    override fun updateCursor(cursorState: ChangeFrameState) {
        mCanvas.scene.cursor = when (cursorState) {
            ChangeFrameState.NOT_RESIZE -> Cursor.DEFAULT
            ChangeFrameState.RIGHT_BOTTOM_RESIZE -> Cursor.NW_RESIZE
            ChangeFrameState.LEFT_TOP_RESIZE -> Cursor.NW_RESIZE
            ChangeFrameState.LEFT_BOTTOM_RESIZE -> Cursor.NE_RESIZE
            ChangeFrameState.RIGHT_TOP_RESIZE -> Cursor.NE_RESIZE
        }
    }

    @FXML
    fun onDrawRectangle() {
        mHomeTabPresenter.onDrawNewRectangle(
            mCanvas.width,
            mCanvas.height
        )
    }

    @FXML
    fun onDrawTriangle() {
        mHomeTabPresenter.onDrawNewTriangle(
            mCanvas.width,
            mCanvas.height
        )
    }

    @FXML
    fun onDrawEllipse() {
        mHomeTabPresenter.onDrawNewEllipse(
            mCanvas.width,
            mCanvas.height
        )
    }

    @FXML
    fun onMouseDragged(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMouseDragged(
            x = mouseEvent.x,
            y = mouseEvent.y,
            parentWidth = mCanvas.width,
            parentHeight = mCanvas.height
        )
    }

    @FXML
    fun onMouseMoved(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMouseMoved(mouseEvent.x, mouseEvent.y)
    }

    @FXML
    fun onMousePressed(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMousePressed(mouseEvent.x, mouseEvent.y)
    }

    @FXML
    fun onKeyPressed(keyEvent: KeyEvent) {
        when (keyEvent.code) {
            KeyCode.DELETE -> mCanvasPresenter.onDeleteShape()
            else -> Unit
        }
    }

    @FXML
    fun onMouseReleased(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMouseReleased(mouseEvent.x, mouseEvent.y)
    }

    @FXML
    fun onUndo() {
        mHomeTabPresenter.onUndo()
    }

    @FXML
    fun onRedo() {
        mHomeTabPresenter.onRedo()
    }

    @FXML
    fun onSaveButtonClicked() {
        //TODO доработать сохранение открытого файла
        val file = mFileChooser.showSaveDialog(mCanvas.scene.window) ?: return
        mFilePresenter.onSave(file)
    }

    @FXML
    fun onOpenButtonClicked() {
        val file = mFileChooser.showOpenDialog(mCanvas.scene.window) ?: return
        mFilePresenter.onOpen(file)
    }

    @FXML
    fun onSaveAsButtonClicked() {
        val file = mFileChooser.showSaveDialog(mCanvas.scene.window) ?: return
        mFilePresenter.onSave(file)
    }

    @FXML
    fun onOpenNewWindow() {
        val layout = StackPane()
        val stage = Stage()
        val canvas = Canvas(800.0, 450.0)
        val graphicsContext = canvas.graphicsContext2D
        layout.children.add(canvas)
        val scene = Scene(layout, 800.0, 450.0)
        stage.isResizable = false
        stage.scene = scene
        stage.show()
        val presenter = PresenterFactory.createCanvasPresenterFor(this, graphicsContext)
        canvas.onMousePressed = EventHandler {
            presenter.onMousePressed(it.x, it.y)
        }
        canvas.onMouseDragged = EventHandler {
            presenter.onMouseDragged(it.x, it.y, canvas.width, canvas.height)
        }
        canvas.onMouseReleased = EventHandler {
            presenter.onMouseReleased(it.x, it.y)
        }
        canvas.onMouseMoved = EventHandler {
            presenter.onMouseMoved(it.x, it.y)
        }
    }

}