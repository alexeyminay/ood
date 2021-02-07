package com.alexey.minay.ood.lab09.ui.presenters

import com.alexey.minay.ood.lab09.application.FileTabInteractor
import com.alexey.minay.ood.lab09.ui.MVP
import java.io.File

class FileTabPresenter(
    private val fileTabInteractor: FileTabInteractor
) : MVP.IFileTabPresenter {

    private var mView: MVP.IFileTabView? = null

    override fun onViewCreated(view: MVP.IFileTabView) {
        mView = view
    }

    override fun onSave(file: File) {
        fileTabInteractor.saveIn(file)
    }

    override fun onSaveAs(file: File) {
        fileTabInteractor.saveAsIn(file)
    }

    override fun onOpen(file: File) {
        fileTabInteractor.open(file)
    }

}