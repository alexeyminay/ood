package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.FillStyle
import com.alexey.minay.ood.lab07.domain.composite.IStyle
import com.alexey.minay.ood.lab07.domain.composite.LineStyle

class CompoundStyle(
        private val calculateFillStyle: () -> FillStyle?,
        private val calculateLineStyle: () -> LineStyle?
) : IStyle {

    override val fillStyle: FillStyle?
        get() = calculateFillStyle()

    override val lineStyle: LineStyle?
        get() = calculateLineStyle()

}