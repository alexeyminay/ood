package com.alexey.minay.ood.lab07.domain.composite.group

import com.alexey.minay.ood.lab07.domain.canvas.FillStyle
import com.alexey.minay.ood.lab07.domain.canvas.ICanvas
import com.alexey.minay.ood.lab07.domain.canvas.LineStyle
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test

class GroupTest {

    private val mCanvas = mock<ICanvas>()
    private val mFillStyle = mock<FillStyle>()
    private val mLineStyle = mock<LineStyle>()
    private val mGroup = Group(
            fillStyle = mFillStyle,
            lineStyle = mLineStyle
    )


}