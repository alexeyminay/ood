package com.alexey.minay.ood.lab09.domain.stateHandler

import com.alexey.minay.ood.lab09.domain.*
import com.alexey.minay.ood.lab09.domain.shapes.Ellipse
import com.alexey.minay.ood.lab09.domain.shapes.Point
import com.alexey.minay.ood.lab09.domain.shapes.Rectangle
import com.alexey.minay.ood.lab09.domain.shapes.Triangle
import com.alexey.minay.ood.lab09.domain.style.Style
import java.util.*
import kotlin.math.pow

class ImageStateHandler : IImageStateHandler {

    override var shapes = mutableListOf<IShape>()
        private set
    override var resizableState: ResizableState = ResizableState.NOT_RESIZE
    private var mPressedPoint: Point? = null

    override fun createImage(shapeType: ShapeType, parentWidth: Double, parentHeight: Double, style: Style.Shape) {
        shapes
                .asReversed()
                .forEach { shape ->
                    shape.isSelected = false
                }
        val shape = when (shapeType) {
            ShapeType.ELLIPSE ->
                Ellipse.createDefault(Point(parentWidth / 2, parentHeight / 2), style)
            ShapeType.RECTANGLE ->
                Rectangle.createDefault(Point(parentWidth / 2, parentHeight / 2), style)
            ShapeType.TRIANGLE ->
                Triangle.createDefault(Point(parentWidth / 2, parentHeight / 2), style)
        }
        shapes.add(shape)
    }

    override fun updateShapesSelection(mousePosition: Point) {
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

    override fun rememberPressedPoint(pressesPoint: Point) {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
        if (shape.isIncluding(pressesPoint)) {
            mPressedPoint = pressesPoint
        }
    }

    override fun deleteSelectedShape() {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
        shapes.remove(shape)
    }

    override fun updateCursor(mousePosition: Point) {
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

    override fun modifySelectedShapeStyle(style: Style.Shape) {
        val shape = shapes.asReversed().firstOrNull { it.isSelected } ?: return
        shape.shapeStyle = style
    }

    override fun moveShape(newPosition: Point, parentWidth: Double, parentHeight: Double) {
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
        shape.frame.leftTop = Point(newLeftTopX, newLeftTopY)
        shape.frame.rightBottom = Point(newRightBottomX, newRightBottomY)
        mPressedPoint = newPosition
    }

    override fun reloadImage(shapes: List<IShape>) {
        this.shapes = shapes.toMutableList()
    }

    private fun IShape.calculateNewLeftTopX(differenceX: Double) =
            when (resizableState) {
                ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.x
                ResizableState.RIGHT_TOP_RESIZE -> frame.leftTop.x
                else -> frame.leftTop.x - differenceX
            }

    private fun IShape.calculateNewLeftTopY(differenceY: Double) =
            when (resizableState) {
                ResizableState.RIGHT_BOTTOM_RESIZE -> frame.leftTop.y
                ResizableState.LEFT_BOTTOM_RESIZE -> frame.leftTop.y
                else -> frame.leftTop.y - differenceY
            }

    private fun IShape.calculateNewRightBottomX(differenceX: Double) =
            when (resizableState) {
                ResizableState.LEFT_BOTTOM_RESIZE -> frame.rightBottom.x
                ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.x
                else -> frame.rightBottom.x - differenceX
            }

    private fun IShape.calculateNewRightBottomY(differenceY: Double) =
            when (resizableState) {
                ResizableState.LEFT_TOP_RESIZE -> frame.rightBottom.y
                ResizableState.RIGHT_TOP_RESIZE -> frame.rightBottom.y
                else -> frame.rightBottom.y - differenceY
            }

    private fun Point.isCross(point: Point): Boolean {
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
            val snapshotShapes = mutableListOf<IShape>()
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
                                ?.isSelected) {
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

        private fun IShape.copy() =
                when (this) {
                    is Triangle -> Triangle(frame.copy(), shapeStyle)
                    is Rectangle -> Rectangle(frame.copy(), shapeStyle)
                    is Ellipse -> Ellipse(frame.copy(), shapeStyle)
                    else -> throw RuntimeException("Incorrect shape type")
                }.let {
                    it.isSelected = isSelected
                    it
                }

    }

}
