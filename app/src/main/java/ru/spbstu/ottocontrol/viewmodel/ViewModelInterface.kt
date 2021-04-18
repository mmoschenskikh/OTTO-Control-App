package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter

interface ViewModelInterface {
    fun askForPermissionToUseBluetoothModule()
    fun notifyThatBluetoothIsNotSupported()
    fun askForTurnBluetoothOn()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
    fun changeListOfPairedDevices()
    fun getDataFromDevice(data: String)
}