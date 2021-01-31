package com.alexey.minay.ood.lab09.domain.repository

import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.domain.*
import com.alexey.minay.ood.lab09.domain.shapes.Point
import java.io.File

class Repository(
        private val imageStateHandler: IImageStateHandler,
        private val imageStateMemento: IStateMemento,
        private val fileHelper: IFileHelper
) : ICanvasRepository, IFileRepository {

    private var mOnNext: (RepositoryResult) -> Unit = { }

    override fun subscribe(onNext: (RepositoryResult) -> Unit) {
        mOnNext = onNext
    }

    override fun createNewShape(shapeType: ShapeType, parentWidth: Double, parentHeight: Double) {
        imageStateMemento.saveState()
        imageStateHandler.createImage(shapeType, parentWidth, parentHeight)
        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
    }

    override fun updateCursor(mousePosition: Point) {
        imageStateHandler.updateCursor(mousePosition)
        mOnNext(RepositoryResult.ResizableStateResult(imageStateHandler.resizableState))
    }

    override fun updateShapesSelection(mousePosition: Point) {
        imageStateMemento.saveState()
        imageStateHandler.updateShapesSelection(mousePosition)
        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
    }

    override fun rememberPressedPoint(pressedPoint: Point) {
        imageStateMemento.saveState()
        imageStateHandler.rememberPressedPoint(pressedPoint)
    }

    override fun moveShape(newCenterPosition: Point, parentWidth: Double, parentHeight: Double) {
        imageStateHandler.moveShape(newCenterPosition, parentWidth, parentHeight)
        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
    }

    override fun deleteSelectedShape() {
        imageStateMemento.saveState()
        imageStateHandler.deleteSelectedShape()
        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
    }

    override fun deleteLastPressedState() {
        imageStateHandler.deleteLastPressState()
    }

//    override fun modifySelectedShapeStyle(style: Style.Shape) {
//        imageStateMemento.saveState()
//        imageStateHandler.modifySelectedShapeStyle(style)
//        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
//    }

    override fun onRedo() {
        imageStateMemento.redo()
        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
    }

    override fun onUndo() {
        imageStateMemento.undo()
        mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
    }

    override fun openFile(file: File) {
        fileHelper.openFile(file) {
            imageStateHandler.reloadImage(it)
            imageStateMemento.deleteSnapshots()
            mOnNext(RepositoryResult.ImageResult(imageStateHandler.shapes))
        }
    }

    override fun saveFile(file: File) {
        fileHelper.saveFile(file, imageStateHandler.shapes)
    }

}