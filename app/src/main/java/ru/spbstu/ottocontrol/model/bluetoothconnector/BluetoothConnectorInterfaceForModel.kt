package ru.spbstu.ottocontrol.model.bluetoothconnector

interface BluetoothConnectorInterfaceForModel {
    fun initBluetoothAdapter()
    fun searchPairedDevices()
}