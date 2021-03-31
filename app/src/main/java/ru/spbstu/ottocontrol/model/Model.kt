package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

class Model(override var viewModel: ViewModelInterfaceForModel) : ModelInterfaceForViewModel {
    override val availableDevices: MutableList<BluetoothDevice> = mutableListOf()

    private val bluetoothConnector = BluetoothConnector(this)


    // ViewModel
    override fun initBluetooth() = bluetoothConnector.initBluetooth()
    override fun searchAvailableDevices() = bluetoothConnector.searchAvailableDevices()


    // BluetoothConnector
    fun addDevice(device: BluetoothDevice) {
        availableDevices.add(device)
        viewModel.notifyViewAboutStateChange()
    }
    fun clearListOfDevices() = availableDevices.clear()
    fun getContext() = viewModel.getContext()
}