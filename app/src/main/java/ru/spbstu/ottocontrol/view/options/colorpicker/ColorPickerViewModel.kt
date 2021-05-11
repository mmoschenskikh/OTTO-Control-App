package ru.spbstu.ottocontrol.view.options.colorpicker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.ActionRepository
import ru.spbstu.ottocontrol.data.FireLed
import ru.spbstu.ottocontrol.viewmodel.ViewModels

class ColorPickerViewModel : ViewModel() {

    init {
        ViewModels.colorPickerViewModel = this
    }

    private val _ledColor = MutableLiveData(0xFFFFFF)
    val ledColor: LiveData<Int> = _ledColor

    fun onColorChanged(newColor: Int) {
        _ledColor.value = newColor
    }

    fun onColorPicked() {
        _ledColor.value?.let { color ->
            ActionRepository.sendAction(FireLed(color))
        }
    }
}
