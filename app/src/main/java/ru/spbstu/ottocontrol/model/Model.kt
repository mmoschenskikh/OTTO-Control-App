package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.IntentFilter
import ru.spbstu.ottocontrol.model.bluetoothconnector.BluetoothConnectorInterfaceForModel
import ru.spbstu.ottocontrol.model.bluetoothconnector.BluetoothConnector
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

class Model(override var viewModel: ViewModelInterfaceForModel) :
    ModelInterfaceForViewModel,
    ModelInterfaceForBluetoothConnector
{
    override val availableDevices: MutableList<BluetoothDevice> = mutableListOf()
    private val bluetoothConnector: BluetoothConnectorInterfaceForModel = BluetoothConnector(this)


    // Calls from ViewModel
    override fun initBluetooth() = bluetoothConnector.initBluetoothAdapter()
    override fun searchAvailableDevices() = bluetoothConnector.searchPairedDevices()

    // Calls from BluetoothConnector
    override fun addDevice(device: BluetoothDevice) {
        availableDevices.add(device)
        viewModel.notifyViewAboutStateChange()
    }
    override fun clearListOfPairedDevices() = availableDevices.clear()
    override fun askForTurnBluetoothOn() = viewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
            = viewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)
}