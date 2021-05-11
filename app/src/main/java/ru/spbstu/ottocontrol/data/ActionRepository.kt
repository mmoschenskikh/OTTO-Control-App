package ru.spbstu.ottocontrol.data

import ru.spbstu.ottocontrol.data.model.Model

object ActionRepository {
    fun sendAction(action: Action) {
        Model.sendDataToDevice(action.toBytes())
    }
}
