package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor

class CompoundFillStyle(
    private val getColor: () -> RGBAColor?,
    private val isStyleEnable: () -> Boolean?,
    private val setColor: (RGBAColor?) -> Unit,
    private val setEnableOrDisable: (Boolean?) -> Unit
) : IFillStyle {

    override var isEnable: Boolean?
        get() = isStyleEnable()
        set(value) = setEnableOrDisable(value)
    override var color: RGBAColor?
        get() = getColor()
        set(value) = setColor(value)

}