package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.Frame
import com.alexey.minay.ood.lab07.domain.ICanvas
import com.alexey.minay.ood.lab07.domain.composite.style.CompoundFillStyle
import com.alexey.minay.ood.lab07.domain.composite.style.CompoundLineStyle
import kotlin.math.max
import kotlin.math.min

class Group : IGroup {

    private val mShapes = mutableListOf<IShape>()

    override val fillStyle: IFillStyle = CompoundFillStyle(
        getColor = ::getFillColor,
        isStyleEnable = ::isFillStyleEnable,
        setColor = ::setFillColor,
        setEnableOrDisable = ::setFillStyleEnableOrDisable
    )

    override val lineStyle: ILineStyle = CompoundLineStyle(
        getColor = ::getLineColor,
        getLineWidth = ::getLineWidth,
        isStyleEnable = ::isLineStyleEnable,
        setColor = ::setLineColor,
        setEnableOrDisable = ::setLineStyleEnableOrDisable,
        setLineWidth = ::setLineWidth
    )

    override var frame: Frame?
        get() = getCurrentFrame()
        set(value) = resizeFrame(value)

    override fun getShapeCount() = mShapes.count()

    override fun getShapeIndexAt(index: Int) = mShapes[index]

    override fun insertShape(shape: IShape, index: Int) {
        mShapes.add(index, shape)
    }

    override fun removeAt(index: Int) {
        if (mShapes.size == 1) return
        mShapes.removeAt(index)
    }

    override fun draw(canvas: ICanvas) {
        drawFrame(canvas)
        mShapes.forEach {
            it.draw(canvas)
        }
    }

    private fun resizeFrame(newFrame: Frame?) {
        if (newFrame == null) return
        val currentFrame = getCurrentFrame() ?: return
        mShapes.forEach {
            val frame = it.frame
            if (frame != null) {
                it.frame = Frame(
                    left = frame.left * newFrame.width / currentFrame.width,
                    right = frame.right * newFrame.width / currentFrame.width,
                    top = frame.top * newFrame.height / currentFrame.height,
                    bottom = frame.bottom * newFrame.height / currentFrame.height
                )
            }
        }
    }

    private fun getCurrentFrame(): Frame? {
        fun calculateFrame(): Frame? {
            var frame: Frame? = null
            mShapes.forEach { shape ->
                val shapeFrame = shape.frame
                if (shapeFrame != null) {
                    if (frame == null) {
                        frame = shapeFrame.copy()
                    } else {
                        frame?.let {
                            it.bottom = max(it.bottom, shapeFrame.bottom)
                            it.top = min(it.top, shapeFrame.top)
                            it.right = max(it.right, shapeFrame.right)
                            it.left = min(it.left, shapeFrame.left)
                        }
                    }
                }
            }
            return frame
        }
        return when (mShapes.size) {
            0 -> null
            1 -> mShapes.first().frame
            else -> calculateFrame()
        }
    }

    private fun drawFrame(canvas: ICanvas) {
        val currentFrame = frame ?: return
        val offset = 5.0
        canvas.setLineColor(RGBAColor.GREY_TRANSLUCENT)
        canvas.setLineWidth(FRAME_LINE_WIDTH)
        canvas.moveTo(currentFrame.left - offset, currentFrame.top - offset)
        canvas.lineTo(currentFrame.left - offset, currentFrame.bottom + offset)
        canvas.lineTo(currentFrame.right + offset, currentFrame.bottom + offset)
        canvas.lineTo(currentFrame.right + offset, currentFrame.top - offset)
        canvas.lineTo(currentFrame.left - offset, currentFrame.top - offset)
        canvas.fill(RGBAColor.TRANSPARENT)
    }

    private fun getFillColor(): RGBAColor? =
        getCompoundProperty {
            it.fillStyle.color
        }

    private fun isFillStyleEnable(): Boolean? =
        getCompoundProperty {
            it.fillStyle.isEnable
        }

    private fun getLineColor(): RGBAColor? =
        getCompoundProperty {
            it.lineStyle.color
        }

    private fun getLineWidth(): Double? =
        getCompoundProperty {
            it.lineStyle.lineWidth
        }

    private fun isLineStyleEnable(): Boolean? =
        getCompoundProperty {
            it.lineStyle.isEnable
        }

    private fun setFillColor(color: RGBAColor?) {
        mShapes.forEach { it.fillStyle.color = color }
    }

    private fun setFillStyleEnableOrDisable(isEnable: Boolean?) {
        mShapes.forEach { it.fillStyle.isEnable = isEnable }
    }

    private fun setLineColor(color: RGBAColor?) {
        mShapes.forEach { it.lineStyle.color = color }
    }

    private fun setLineStyleEnableOrDisable(isEnable: Boolean?) {
        mShapes.forEach { it.lineStyle.isEnable = isEnable }
    }

    private fun setLineWidth(width: Double?) {
        mShapes.forEach { it.lineStyle.lineWidth = width }
    }

    private fun <T> getCompoundProperty(property: (IShape) -> T?): T? =
        when (mShapes.size) {
            0 -> null
            1 -> property(mShapes.first())
            else -> {
                val hasAllTheSameProperties = mShapes.all {
                    property(it) == property(mShapes.first())
                }
                when {
                    hasAllTheSameProperties -> property(mShapes.first())
                    else -> null
                }
            }
        }

    companion object {
        private const val FRAME_LINE_WIDTH = 1.0
    }

}