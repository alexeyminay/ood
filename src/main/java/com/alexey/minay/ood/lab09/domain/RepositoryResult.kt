package com.alexey.minay.ood.lab09.domain

import com.alexey.minay.ood.lab09.application.ResizableState

sealed class RepositoryResult {
    class ImageResult(val state: List<IShape>) : RepositoryResult()
    class ResizableStateResult(val state: ResizableState) : RepositoryResult()
}