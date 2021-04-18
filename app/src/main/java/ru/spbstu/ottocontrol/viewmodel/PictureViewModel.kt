package ru.spbstu.ottocontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class PictureViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()

    init { ViewModels.pictureViewModel = this }

    fun onClickMatrixDiag() = model.sendDataToDevice("matrix 0:0 1:1 2:2 3:3")

    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}