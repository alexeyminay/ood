package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.FillStyle
import com.alexey.minay.ood.lab07.domain.composite.IStyle
import com.alexey.minay.ood.lab07.domain.composite.LineStyle

class CompoundStyle(
    private val calculateFillStyle: () -> FillStyle?,
    private val calculateLineStyle: () -> LineStyle?,
    private val setFillStyle: (FillStyle?) -> Unit,
    private val setLineStyle: (LineStyle?) -> Unit
) : IStyle {

    override var fillStyle: FillStyle?
        get() = calculateFillStyle()
        set(value) = setFillStyle(value)

    override var lineStyle: LineStyle?
        get() = calculateLineStyle()
        set(value) = setLineStyle(value)

}