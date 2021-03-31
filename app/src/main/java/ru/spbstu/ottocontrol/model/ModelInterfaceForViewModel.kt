package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

interface ModelInterfaceForViewModel {
    var viewModel: ViewModelInterfaceForModel
    val availableDevices: MutableList<BluetoothDevice>


    fun initBluetooth()
    fun searchAvailableDevices()
}