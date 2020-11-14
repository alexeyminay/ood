package com.alexey.minay.ood.lab09.ui.view

import com.alexey.minay.ood.lab09.PresenterFactory
import com.alexey.minay.ood.lab09.domain.Point
import com.alexey.minay.ood.lab09.domain.ResizePointCrossState
import com.alexey.minay.ood.lab09.domain.style.Style
import com.alexey.minay.ood.lab09.ui.MVP
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.Cursor
import javafx.scene.canvas.Canvas
import javafx.scene.canvas.GraphicsContext
import javafx.scene.control.ColorPicker
import javafx.scene.control.TabPane
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.input.MouseEvent
import javafx.scene.paint.Color
import javafx.stage.FileChooser
import java.net.URL
import java.util.*

class MainView : MVP.ICanvasView, MVP.IFileTabView, Initializable {

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
    private val mFileChooser by lazy {
        FileChooser().apply {
//            extensionFilters.addAll(
//                    FileChooser.ExtensionFilter("JSON", ".json"),
//                    FileChooser.ExtensionFilter("All files", ".")
//            )
        }
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        mCanvasPresenter = PresenterFactory.createCanvasPresenterFor(this)
        mFilePresenter = PresenterFactory.createFilePresenterFor(this)
        mStrokePicker.value = Color.CADETBLUE
        mFillPicker.value = Color.BISQUE
        setColorPaneActionListeners()
        mTabPane.selectionModel.selectLast()
    }

    override fun updateColorPane(style: Style.Shape) {
        mFillPicker.value = style.fillColor.asFxColor()
        mStrokePicker.value = style.strokeColor.asFxColor()
    }

    @FXML
    override fun updateCursor(cursorState: ResizePointCrossState) {
        mCanvas.scene.cursor = when (cursorState) {
            ResizePointCrossState.NOT_CROSS -> Cursor.DEFAULT
            ResizePointCrossState.RIGHT_BOTTOM_RESIZE -> Cursor.NW_RESIZE
            ResizePointCrossState.LEFT_TOP_RESIZE -> Cursor.NW_RESIZE
            ResizePointCrossState.LEFT_BOTTOM_RESIZE -> Cursor.NE_RESIZE
            ResizePointCrossState.RIGHT_TOP_RESIZE -> Cursor.NE_RESIZE
        }
    }

    @FXML
    fun onDrawRectangle() {
        mCanvasPresenter.onDrawNewRectangle(mCanvas.width,
                mCanvas.height,
                createStyle(mStrokePicker.value, mFillPicker.value))
    }

    @FXML
    fun onDrawTriangle() {
        mCanvasPresenter.onDrawNewTriangle(mCanvas.width,
                mCanvas.height,
                createStyle(mStrokePicker.value, mFillPicker.value))
    }

    @FXML
    fun onDrawEllipse() {
        mCanvasPresenter.onDrawNewEllipse(mCanvas.width,
                mCanvas.height,
                createStyle(mStrokePicker.value, mFillPicker.value))
    }

    @FXML
    fun onMouseDragged(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMoveShape(
                newPosition = Point(mouseEvent.x, mouseEvent.y),
                parentWidth = mCanvas.width,
                parentHeight = mCanvas.height
        )
    }

    @FXML
    fun onMouseMoved(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMouseMoved(Point(mouseEvent.x, mouseEvent.y))
    }

    @FXML
    fun onMouseClicked(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMouseClicked(Point(mouseEvent.x, mouseEvent.y))
    }

    @FXML
    fun onMousePressed(mouseEvent: MouseEvent) {
        mCanvasPresenter.onMousePressed(Point(mouseEvent.x, mouseEvent.y))
    }

    @FXML
    fun onKeyPressed(keyEvent: KeyEvent) {
        when (keyEvent.code) {
            KeyCode.DELETE -> mCanvasPresenter.onDeleteShape()
            else -> Unit
        }
    }

    @FXML
    fun onMouseReleased() {
        mCanvasPresenter.onMouseReleased()
    }

    @FXML
    fun onUndo() {
        mCanvasPresenter.onUndo()
    }

    @FXML
    fun onRedo() {
        mCanvasPresenter.onRedo()
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

    private fun setColorPaneActionListeners() {
        mStrokePicker.setOnAction {
            mCanvasPresenter.onStyleModified(createStyle(mStrokePicker.value, mFillPicker.value))
        }
        mFillPicker.setOnAction {
            mCanvasPresenter.onStyleModified(createStyle(mStrokePicker.value, mFillPicker.value))
        }
    }

    private fun createStyle(strokeColor: Color, fillColor: Color) =
            Style.Shape(strokeColor.asDomainColor(), fillColor.asDomainColor())

}