package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter

interface ViewModelInterface {
    fun askForPermissionToUseBluetoothModule()
    fun notifyThatBluetoothIsNotSupported()
    fun notifyThatChosenDeviceIsNotAvailable()
    fun askForTurnBluetoothOn()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
    fun changeListOfAvailableDevices()
    fun getDataFromDevice(data: String)
}