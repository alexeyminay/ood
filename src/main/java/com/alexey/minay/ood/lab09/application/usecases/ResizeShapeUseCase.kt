package com.alexey.minay.ood.lab09.application.usecases

import com.alexey.minay.ood.lab09.application.*
import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint

class ResizeShapeUseCase(
    private val shapeSelectionModel: ShapeSelectionModel,
    private val history: CommandHistory,
    private val document: ApplicationDocument,
    private val documentAdapter: DocumentAdapter,
    private val framePositionCalculator: FramePositionCalculator
) {

    private var mStartResizePoint: AppPoint? = null
    private var mResizableState: ChangeFrameState = ChangeFrameState.NOT_RESIZE
    private var mNewFrame: AppFrame? = null
    private var mTargetShape: IAppShape? = null

    fun startMoving(startResizePoint: AppPoint) {
        
    }

    fun commit() {
        
    }

    fun moveShape(newPosition: AppPoint, parentWidth: Double, parentHeight: Double) {

    }

}