package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame
import com.alexey.minay.ood.lab09.application.common.AppPoint

interface IAppShape: IDrawable {
    val uid: Long
    var frame: AppFrame
    fun isIncluding(point: AppPoint): Boolean
}