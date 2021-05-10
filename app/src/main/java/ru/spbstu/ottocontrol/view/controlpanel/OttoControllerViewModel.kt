package ru.spbstu.ottocontrol.view.controlpanel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.Action
import ru.spbstu.ottocontrol.data.ActionRepository

class OttoControllerViewModel : ViewModel() {

    private val _ledColor = MutableLiveData(0xFFFFFF)
    val ledColor: LiveData<Int> = _ledColor

    fun onAction(action: Action) {
        ActionRepository.sendAction(action)
    }

    fun onColorChanged(newColor: Int) {
        _ledColor.value = newColor
    }

    fun onColorPicked() {
        // TODO
    }
}
