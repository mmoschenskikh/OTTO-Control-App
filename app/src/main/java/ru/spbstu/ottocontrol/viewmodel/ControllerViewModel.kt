package ru.spbstu.ottocontrol.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class ControllerViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()
    var showToast = false // crutch!

    init { ViewModels.controllerViewModel = this }

    fun onClickLeft() = model.sendDataToDevice("step left")
    fun onClickRight() = model.sendDataToDevice("step right")
    fun onClickForward() = model.sendDataToDevice("step forward")
    fun onClickBack() = model.sendDataToDevice("step back")

    fun showCommandExecutedByRobot(command: String) {
        showToast = true // crutch!
        toastShort.value = command
    }
}