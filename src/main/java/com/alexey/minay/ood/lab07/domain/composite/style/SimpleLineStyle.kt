package com.alexey.minay.ood.lab07.domain.composite.style

import com.alexey.minay.ood.lab07.domain.composite.ILineStyle
import com.alexey.minay.ood.lab07.domain.composite.RGBAColor

class SimpleLineStyle(
    override var isEnable: Boolean? = true,
    override var color: RGBAColor? = RGBAColor.BLUE,
    override var lineWidth: Double? = 3.0
) : ILineStyle