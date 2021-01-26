package com.alexey.minay.ood.lab07.domain.composite

import com.alexey.minay.ood.lab07.domain.RGBAColor

data class FillStyle(val isEnable: Boolean, val color: RGBAColor = RGBAColor.TRANSPARENT)