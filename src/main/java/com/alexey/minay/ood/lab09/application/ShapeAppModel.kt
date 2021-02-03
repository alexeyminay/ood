package com.alexey.minay.ood.lab09.application

import com.alexey.minay.ood.lab09.application.common.AppFrame

class ShapeAppModel(
    val shape: IAppShape
) {

    val frame: AppFrame
        get() = shape.frame

}