package ru.spbstu.ottocontrol.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface

class PianoViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()

    init { ViewModels.pianoViewModel = this }

    fun onClickPianoA() = model.sendDataToDevice("piano a")

    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}