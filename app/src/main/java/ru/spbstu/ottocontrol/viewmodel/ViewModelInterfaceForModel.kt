package ru.spbstu.ottocontrol.viewmodel

import android.content.Context
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel

interface ViewModelInterfaceForModel {
    var model: ModelInterfaceForViewModel


    fun notifyViewAboutStateChange()
    fun getContext(): Context
}