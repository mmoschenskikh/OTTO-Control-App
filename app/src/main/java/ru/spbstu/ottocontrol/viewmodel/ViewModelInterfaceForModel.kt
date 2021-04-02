package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter

interface ViewModelInterfaceForModel {
    fun askForPermissionToUseBluetoothModule()
    fun notifyThatBluetoothIsNotSupported()
    fun askForTurnBluetoothOn()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
    fun changeListOfPairedDevices()

    fun getAnswerFromModel(answer: String)
}