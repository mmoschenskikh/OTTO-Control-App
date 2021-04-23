package ru.spbstu.ottocontrol.ui.controlpanel

import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.Action
import ru.spbstu.ottocontrol.data.ActionRepository

class OttoControlPanelViewModel : ViewModel() {

    private val repository = ActionRepository

    fun onActionClicked(action: Action) {
        repository.sendAction(action)
    }
}
