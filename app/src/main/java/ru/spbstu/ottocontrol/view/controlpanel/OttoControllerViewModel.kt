package ru.spbstu.ottocontrol.view.controlpanel

import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.Action
import ru.spbstu.ottocontrol.data.ActionRepository

class OttoControllerViewModel : ViewModel() {

    fun onAction(action: Action) {
        ActionRepository.sendAction(action)
    }
}
