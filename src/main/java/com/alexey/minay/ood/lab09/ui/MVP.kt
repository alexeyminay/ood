package com.alexey.minay.ood.lab09.ui

import com.alexey.minay.ood.lab09.application.common.AppPoint
import com.alexey.minay.ood.lab09.application.ResizableState
import java.io.File

interface MVP {

    interface ICanvasView {
        fun updateCursor(cursorState: ResizableState)
    }

    interface ICanvasPresenter {
        fun onViewCreated(view: ICanvasView)
        fun onMouseDragged(x: Double, y: Double, parentWidth: Double, parentHeight: Double)
        fun onMouseMoved(mousePosition: AppPoint)
        fun onMouseClicked(x: Double, y: Double)
        fun onMousePressed(x: Double, y: Double)
        fun onDeleteShape()
        fun onMouseReleased()
        fun onStyleModified()
        fun onUndo()
        fun onRedo()
    }

    interface IHomeTabPresenter {
        fun onDrawNewRectangle(parentWidth: Double, parentHeight: Double)
        fun onDrawNewTriangle(parentWidth: Double, parentHeight: Double)
        fun onDrawNewEllipse(parentWidth: Double, parentHeight: Double)
    }

    interface IFileTabView

    interface IFileTabPresenter {
        fun onViewCreated(view: IFileTabView)
        fun onSave(file: File)
        fun onSaveAs(file: File)
        fun onOpen(file: File)
    }

}