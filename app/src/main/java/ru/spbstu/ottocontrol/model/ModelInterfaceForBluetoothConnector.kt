package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.IntentFilter

interface ModelInterfaceForBluetoothConnector {
    fun addDevice(device: BluetoothDevice)
    fun clearListOfPairedDevices()
    fun askForTurnBluetoothOn()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
}