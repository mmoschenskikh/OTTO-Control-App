package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice

interface ModelInterface {
    fun initBluetooth()
    fun searchPairedDevices()
    fun getPairedDevices(): MutableList<BluetoothDevice>
    fun connectToDevice(index: Int)
    fun sendDataToDevice(data: ByteArray)
    fun closeDeviceConnection()
}
