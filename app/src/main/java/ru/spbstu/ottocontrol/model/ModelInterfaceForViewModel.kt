package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

interface ModelInterfaceForViewModel {
    fun initBluetooth()
    fun searchPairedDevices()
    fun getPairedDevices(): MutableList<BluetoothDevice>
    fun connectToDevice(index: Int)
    fun sendCommandToDevice(command: String)
    fun closeDeviceConnection()

    fun askModelFromViewModel(question: String)
}