package ru.spbstu.ottocontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class PictureViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()

    init { ViewModels.pictureViewModel = this }

    fun onClickSetBlack() = model.sendDataToDevice("matrix all to black")
    fun onClickSetWhite() = model.sendDataToDevice("matrix all to white")
    fun onClickSetBlack(i: Int, j: Int) = model.sendDataToDevice("matrix [$i; $j] from white to black" )
    fun onClickSetWhite(i: Int, j: Int) = model.sendDataToDevice("matrix [$i; $j] from black to white" )

    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}