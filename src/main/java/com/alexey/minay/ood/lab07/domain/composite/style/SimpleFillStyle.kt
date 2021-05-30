package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.IFillStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor

class SimpleFillStyle(
    override var isEnable: Boolean? = true,
    override var color: RGBAColor? = RGBAColor.TRANSPARENT
) : IFillStyle
