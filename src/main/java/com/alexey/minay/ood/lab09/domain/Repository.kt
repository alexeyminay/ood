package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.stateHandler.ImageStateHandler
import com.alexey.minay.ood.lab09.domain.style.Style
import java.io.File

class Repository(
        private val imageStateHandler: ImageStateHandler,
        private val imageStateMemento: IStateMemento,
        private val fileHelper: IFileHelper
) : ICanvasRepository, IFileRepository {

    private var mOnNext: (ScreenStateChanges) -> Unit = { }

    override fun subscribe(onNext: (ScreenStateChanges) -> Unit) {
        mOnNext = onNext
    }

    override fun createNewShape(shapeType: ShapeType, parentWidth: Double, parentHeight: Double, style: Style.Shape) {
        imageStateMemento.saveState()
        imageStateHandler.createImage(shapeType, parentWidth, parentHeight, style)
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun updateCursor(mousePosition: Point) {
        imageStateHandler.updateCursor(mousePosition)
        mOnNext(ScreenStateChanges.ResizableState(imageStateHandler.resizableState))
    }

    override fun updateShapesSelection(mousePosition: Point) {
        imageStateMemento.saveState()
        imageStateHandler.updateShapesSelection(mousePosition)
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun rememberPressedPoint(pressedPoint: Point) {
        imageStateMemento.saveState()
        imageStateHandler.rememberPressedPoint(pressedPoint)
    }

    override fun moveShape(newCenterPosition: Point, parentWidth: Double, parentHeight: Double) {
        imageStateHandler.moveShape(newCenterPosition, parentWidth, parentHeight)
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun deleteSelectedShape() {
        imageStateMemento.saveState()
        imageStateHandler.deleteSelectedShape()
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun deleteLastPressedState() {
        imageStateHandler.deleteLastPressState()
    }

    override fun modifySelectedShapeStyle(style: Style.Shape) {
        imageStateMemento.saveState()
        imageStateHandler.modifySelectedShapeStyle(style)
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun onRedo() {
        imageStateMemento.redo()
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun onUndo() {
        imageStateMemento.undo()
        mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
    }

    override fun openFile(file: File) {
        fileHelper.openFile(file) {
            imageStateHandler.reloadImage(it)
            imageStateMemento.deleteSnapshots()
            mOnNext(ScreenStateChanges.ImageState(imageStateHandler.shapes))
        }
    }

    override fun saveFile(file: File) {
        fileHelper.saveFile(file, imageStateHandler.shapes)
    }

}