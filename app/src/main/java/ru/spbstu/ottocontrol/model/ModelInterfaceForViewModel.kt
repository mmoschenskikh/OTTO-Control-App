package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

interface ModelInterfaceForViewModel {
    fun initBluetooth()
    fun searchPairedDevices()
    fun getPairedDevices(): MutableList<BluetoothDevice>

    fun askModelFromViewModel(question: String)
}