package com.alexey.minay.ood.lab09.domain.stateHandler

import com.alexey.minay.ood.lab09.application.ResizableState
import com.alexey.minay.ood.lab09.application.ShapeType
import com.alexey.minay.ood.lab09.domain.IImageStateHandler
import com.alexey.minay.ood.lab09.application.IAppShape
import com.alexey.minay.ood.lab09.domain.IStateMemento
import com.alexey.minay.ood.lab09.application.common.AppPoint
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.*
import kotlin.math.pow

class State : IImageStateHandler {

    override var shapes = mutableListOf<IAppShape>()
        private set
    override var resizableState: ResizableState = ResizableState.NOT_RESIZE
    private var mPressedPoint: AppPoint? = null

    //
    //
    //

    val state: Observable<MutableList<IAppShape>>
        get() = mState
    private val mState = BehaviorSubject.createDefault(mutableListOf<IAppShape>())


    fun getShapeCount() = mState.value.size

    fun getShape(index: Int): IAppShape = mState.value[index]

    fun insertShapeAt(index: Int, shape: IAppShape) {
        mState.onNext(mState.value.apply { add(index, shape) })
    }

    fun removeShapeAt(index: Int) = mState.value.removeAt(index)


    //
    //
    //

    override fun createImage(shapeType: ShapeType, parentWidth: Double, parentHeight: Double) {
//        shapes
//            .asReversed()
//            .forEach { shape ->
//                shape.isSelected = false
//            }
//        val shape = when (shapeType) {
//            ShapeType.ELLIPSE ->
//                Ellipse.createDefault(Point(parentWidth / 2, parentHeight / 2), style)
//            ShapeType.RECTANGLE ->
//                Rectangle.createDefault(Point(parentWidth / 2, parentHeight / 2), style)
//            ShapeType.TRIANGLE ->
//                Triangle.createDefault(Point(parentWidth / 2, parentHeight / 2), style)
//        }
//        shapes.add(shape)
        TODO()
    }

    override fun updateShapesSelection(mousePosition: AppPoint) {
        var isSelected = false
        shapes
            .asReversed()
            .forEach { shape ->
                if (shape.isIncluding(mousePosition) && !isSelected) {
                    shape.isSelected = true
                    isSelected = true
                } else {
                    shape.isSelected = false
                }
            }
    }

    override fun rememberPressedPoint(pressesPoint: AppPoint) {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
        if (shape.isIncluding(pressesPoint)) {
            mPressedPoint = pressesPoint
        }
    }

    override fun deleteSelectedShape() {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
        shapes.remove(shape)
    }

    override fun updateCursor(mousePosition: AppPoint) {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return

        resizableState = when {
            mousePosition.isCross(shape.frame.rightBottom) -> ResizableState.RIGHT_BOTTOM_RESIZE
            mousePosition.isCross(shape.frame.leftTop) -> ResizableState.LEFT_TOP_RESIZE
            mousePosition.isCross(shape.frame.rightTop) -> ResizableState.RIGHT_TOP_RESIZE
            mousePosition.isCross(shape.frame.leftBottom) -> ResizableState.LEFT_BOTTOM_RESIZE
            else -> ResizableState.NOT_RESIZE
        }
    }

    override fun deleteLastPressState() {
        mPressedPoint = null
        resizableState = ResizableState.NOT_RESIZE
    }

//    override fun modifySelectedShapeStyle(style: Style.Shape) {
//        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
//    }

    override fun moveShape(newPosition: AppPoint, parentWidth: Double, parentHeight: Double) {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
        if (mPressedPoint == null) {
            mPressedPoint = newPosition
            return
        }
        val oldPosition = mPressedPoint ?: return
        val differenceX = oldPosition.x - newPosition.x
        val differenceY = oldPosition.y - newPosition.y
        var newLeftTopX = shape.calculateNewLeftTopX(differenceX)
        var newLeftTopY = shape.calculateNewLeftTopY(differenceY)
        var newRightBottomX = shape.calculateNewRightBottomX(differenceX)
        var newRightBottomY = shape.calculateNewRightBottomY(differenceY)
        val offset = 1.0
        val shapeWidth = shape.frame.rightBottom.x - shape.frame.leftBottom.x
        val shapeHeight = shape.frame.leftBottom.y - shape.frame.leftTop.y
        if (newLeftTopX + offset > parentWidth - shapeWidth) {
            newLeftTopX = parentWidth - shapeWidth - offset
            newRightBottomX = parentWidth - offset
        }
        if (newLeftTopX - offset < 0.0) {
            newLeftTopX = 0.0 + offset
            newRightBottomX = shapeWidth + offset
        }
        if (newLeftTopY + offset > parentHeight - shapeHeight) {
            newLeftTopY = parentHeight - shapeHeight - offset
            newRightBottomY = parentHeight - offset
        }
        if (newLeftTopY < 0.0) {
            newLeftTopY = 0.0 + offset
            newRightBottomY = shapeHeight + offset
        }
        shape.frame.leftTop = AppPoint(newLeftTopX, newLeftTopY)
        shape.frame.rightBottom = AppPoint(newRightBottomX, newRightBottomY)
        mPressedPoint = newPosition
    }

    override fun reloadImage(shapes: List<IAppShape>) {
        this.shapes = shapes.toMutableList()
    }

    private fun IAppShape.calculateNewLeftTopX(differenceX: Double) =
        when (resizableState) {
            ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.x
            ResizableState.RIGHT_TOP_RESIZE -> frame.leftTop.x
            else -> frame.leftTop.x - differenceX
        }

    private fun IAppShape.calculateNewLeftTopY(differenceY: Double) =
        when (resizableState) {
            ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.y
            ResizableState.LEFT_BOTTOM_RESIZE -> frame.leftTop.y
            else -> frame.leftTop.y - differenceY
        }

    private fun IAppShape.calculateNewRightBottomX(differenceX: Double) =
        when (resizableState) {
            ResizableState.LEFT_BOTTOM_RESIZE -> frame.rightBottom.x
            ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.x
            else -> frame.rightBottom.x - differenceX
        }

    private fun IAppShape.calculateNewRightBottomY(differenceY: Double) =
        when (resizableState) {
            ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.y
            ResizableState.RIGHT_TOP_RESIZE -> frame.rightBottom.y
            else -> frame.rightBottom.y - differenceY
        }

    private fun AppPoint.isCross(point: AppPoint): Boolean {
        val resizeChangePositionRadius = 3.0
        return (x - point.x).pow(2) +
                (y - point.y).pow(2) <= resizeChangePositionRadius.pow(2)
    }

    inner class ImageStateMemento : IStateMemento {

        private val mState = ArrayDeque<ImageStateSnapshot>()
        private val mCanceledState = ArrayDeque<ImageStateSnapshot>()

        override fun undo() {
            if (!canUndo()) return
            shapes = mState
                .poll()
                .shapes
                .toMutableList()
            saveState(mCanceledState)
        }

        override fun redo() {
            if (!canRedo()) return
            shapes = mCanceledState
                .poll()
                .shapes
                .toMutableList()
            saveState(mState)
        }

        override fun saveState() {
            saveState(mState)
            mCanceledState.clear()
        }

        override fun canRedo() = mCanceledState.isNotEmpty()

        override fun canUndo() = mState.isNotEmpty()

        override fun deleteSnapshots() {
            mCanceledState.clear()
            mState.clear()
        }

        private fun saveState(state: ArrayDeque<ImageStateSnapshot>) {
            val snapshotShapes = mutableListOf<IAppShape>()
            var hasChanges = false
            if (shapes.size != state.firstOrNull()?.shapes?.size) hasChanges = true
            shapes.forEachIndexed { index, shape ->
                if (shape.frame != state
                        .firstOrNull()
                        ?.shapes
                        ?.getOrNull(index)
                        ?.frame
                    || shape.isSelected != state
                        .firstOrNull()
                        ?.shapes
                        ?.getOrNull(index)
                        ?.isSelected
                ) {
                    hasChanges = true
                }
                snapshotShapes.add(shape.copy())
            }
            if (hasChanges) {
                state.push(ImageStateSnapshot(shapes = snapshotShapes))
            }
            if (state.size > 10) {
                state.pollLast()
            }
        }

        private fun IAppShape.copy(): IAppShape {
            TODO()
        }
//            when (this) {
//                is Triangle -> Triangle(frame.copy(), shapeStyle)
//                is Rectangle -> Rectangle(frame.copy(), shapeStyle)
//                is Ellipse -> Ellipse(frame.copy(), shapeStyle)
//                else -> throw RuntimeException("Incorrect shape type")
//            }.let {
//                it.isSelected = isSelected
//                it
//            }

    }

}
