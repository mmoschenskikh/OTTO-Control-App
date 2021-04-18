package ru.spbstu.ottocontrol.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class ControllerViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()

    init { ViewModels.controllerViewModel = this }

    fun onClickLeft() = model.sendDataToDevice("left")
    fun onClickRight() = model.sendDataToDevice("right")
    fun onClickForward() = model.sendDataToDevice("forward")
    fun onClickBack() = model.sendDataToDevice("back")

    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}