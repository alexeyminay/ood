package com.alexey.minay.ood.lab04

class Painter {

    fun drawPicture(draft: PictureDraft, canvas: ICanvas) {
        for (i in 0 until draft.getShapeCount()) {
            draft.getShape(i).draw(canvas)
        }
    }

}