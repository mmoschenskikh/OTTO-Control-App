package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice

interface ModelInterface {
    fun initBluetooth()
    fun searchAvailableDevices()
    fun getListOfAvailableDevices(): List<BluetoothDevice>
    fun getListOfPairedDevices(): List<BluetoothDevice>
    fun connectToDevice(index: Int)
    fun sendDataToDevice(data: String)
    fun closeDeviceConnection()
}