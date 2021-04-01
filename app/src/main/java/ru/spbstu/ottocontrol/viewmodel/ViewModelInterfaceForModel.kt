package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel

interface ViewModelInterfaceForModel {
    fun askForAccessToBluetoothModule()
    fun askForTurnBluetoothOn()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
    fun changeListOfPairedDevices()

    fun getAnswerFromModel(answer: String)
}