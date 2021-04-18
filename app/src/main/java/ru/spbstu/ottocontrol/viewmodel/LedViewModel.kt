package ru.spbstu.ottocontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class LedViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()

    init { ViewModels.ledViewModel = this }

    fun onClickLedRed() = model.sendDataToDevice("led #FF0000")

    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}