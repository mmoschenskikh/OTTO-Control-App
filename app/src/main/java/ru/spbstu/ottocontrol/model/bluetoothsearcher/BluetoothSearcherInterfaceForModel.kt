package ru.spbstu.ottocontrol.model.bluetoothsearcher

interface BluetoothSearcherInterfaceForModel {
    fun initBluetoothAdapter()
    fun searchPairedDevices()
}