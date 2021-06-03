package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor

class CompoundFillStyle(
    private val getStyles: () -> List<IFillStyle>,
) : IFillStyle {

    override var isEnable: Boolean?
        get() = mStyles.getCompoundProperty { isEnable }
        set(value) = mStyles.setCompoundProperty { it.isEnable = value }

    override var color: RGBAColor?
        get() = mStyles.getCompoundProperty { color }
        set(value) = mStyles.setCompoundProperty { it.color = value }

    private val mStyles: List<IFillStyle>
        get() = getStyles()

    private fun <T> List<IFillStyle>.getCompoundProperty(
        target: IFillStyle.() -> T?
    ): T? = when (size) {
        0 -> null
        1 -> first().target()
        else -> {
            val hasAllTheSameProperties = all {
                it.target() == first().target()
            }
            when {
                hasAllTheSameProperties -> first().target()
                else -> null
            }
        }
    }

    private fun List<IFillStyle>.setCompoundProperty(setValue: (IFillStyle) -> Unit) {
        forEach { setValue(it) }
    }

}