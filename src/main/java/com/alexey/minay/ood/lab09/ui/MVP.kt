package com.alexey.minay.ood.lab09.ui

import com.alexey.minay.ood.lab09.application.usecases.ChangeFrameState
import com.alexey.minay.ood.lab09.application.common.AppPoint
import java.io.File

interface MVP {

    interface ICanvasView {
        fun updateCursor(cursorState: ChangeFrameState)
    }

    interface ICanvasPresenter {
        fun onViewCreated(view: ICanvasView)
        fun onMouseDragged(x: Double, y: Double, parentWidth: Double, parentHeight: Double)
        fun onMouseMoved(x: Double, y: Double)
        fun onMousePressed(x: Double, y: Double, isControlDown: Boolean)
        fun onDeleteShape()
        fun onMouseReleased(x: Double, y: Double)
    }

    interface IHomeTabPresenter {
        fun onDrawNewRectangle(parentWidth: Double, parentHeight: Double)
        fun onDrawNewTriangle(parentWidth: Double, parentHeight: Double)
        fun onDrawNewEllipse(parentWidth: Double, parentHeight: Double)
        fun onUndo()
        fun onRedo()
    }

    interface IFileTabView

    interface IFileTabPresenter {
        fun onViewCreated(view: IFileTabView)
        fun onSave(file: File)
        fun onSaveAs(file: File)
        fun onOpen(file: File)
    }

}