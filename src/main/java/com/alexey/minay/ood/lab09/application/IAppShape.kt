package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.domain.ICanvas
import com.alexey.minay.ood.lab09.application.common.AppPoint

interface IAppShape {
    var frame: AppFrame
    var isSelected: Boolean
    fun draw(canvasAdapter: ICanvas)
    fun isIncluding(point: AppPoint): Boolean
}