package ru.spbstu.ottocontrol.data.model

import android.bluetooth.BluetoothDevice

interface ModelInterface {
    fun initBluetooth()
    fun searchAvailableDevices()
    fun getListOfAvailableDevices(): List<BluetoothDevice>
    fun getListOfPairedDevices(): List<BluetoothDevice>
    fun connectToPairedDevice(index: Int)
    fun connectToAvailableDevice(index: Int)
    fun sendDataToDevice(data: ByteArray)
    fun closeDeviceConnection()
}
