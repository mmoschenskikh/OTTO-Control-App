package ru.spbstu.ottocontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class PictureViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()

    init { ViewModels.pictureViewModel = this }


    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}
