package ru.spbstu.ottocontrol.data

import ru.spbstu.ottocontrol.data.model.Model

// FIXME: Probably should be turned into a more general repository (creating several repository classes is OK too)
object ActionRepository {
    fun sendAction(action: Action) {
        Model.sendDataToDevice(action.toBytes())
    }
}
