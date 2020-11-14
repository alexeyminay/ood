package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.shapes.IShape

sealed class ScreenStateChanges {
    class ImageState(val imageState: List<IShape>) : ScreenStateChanges()
    class CursorState(val cursorState: ResizePointCrossState) : ScreenStateChanges()
}