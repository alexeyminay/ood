package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor

class CompoundLineStyle(
    private val getColor: () -> RGBAColor?,
    private val isStyleEnable: () -> Boolean?,
    private val setColor: (RGBAColor?) -> Unit,
    private val setEnableOrDisable: (Boolean?) -> Unit,
    private val getLineWidth: () -> Double?,
    private val setLineWidth: (Double?) -> Unit
) : ILineStyle {

    override var isEnable: Boolean?
        get() = isStyleEnable()
        set(value) = setEnableOrDisable(value)
    override var color: RGBAColor?
        get() = getColor()
        set(value) = setColor(value)
    override var lineWidth: Double?
        get() = getLineWidth()
        set(value) = setLineWidth(value)

}