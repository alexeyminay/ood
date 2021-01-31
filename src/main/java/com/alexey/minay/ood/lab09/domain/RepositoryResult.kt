package com.alexey.minay.ood.lab09.domain

sealed class RepositoryResult {
    class ImageResult(val state: List<IShape>) : RepositoryResult()
    class ResizableStateResult(val state: ResizableState) : RepositoryResult()
}