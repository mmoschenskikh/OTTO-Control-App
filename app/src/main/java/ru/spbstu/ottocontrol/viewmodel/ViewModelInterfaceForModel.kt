package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel

interface ViewModelInterfaceForModel {
    fun notifyViewAboutStateChange()
    fun askForTurnBluetoothOn()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)

    fun getAnswerFromModel(answer: String)
}