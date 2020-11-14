package com.alexey.minay.ood.lab09.ui.view

import com.alexey.minay.ood.lab09.domain.style.Color


fun Color.asFxColor(alpha: Double = 1.0) =
        javafx.scene.paint.Color(
                red, green, blue, alpha
        )


fun javafx.scene.paint.Color.asDomainColor() =
        Color(
                red = red,
                green = green,
                blue = blue
        )