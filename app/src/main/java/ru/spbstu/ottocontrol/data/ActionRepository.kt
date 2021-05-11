package ru.spbstu.ottocontrol.data

import android.util.Log

object ActionRepository {
    fun sendAction(action: Action) {
        Log.d("ActionRepository", "Action sent to the robot: $action")
//        Model.sendDataToDevice(action.toBytes())
    }
}
