package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.domain.stateHandler.IShape

sealed class ScreenStateChanges {
    class ImageState(val imageState: List<IShape>) : ScreenStateChanges()
    class ResizableState(val resizableState: Resizable) : ScreenStateChanges()
}