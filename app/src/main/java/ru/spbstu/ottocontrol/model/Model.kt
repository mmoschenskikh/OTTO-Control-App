package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.IntentFilter
import ru.spbstu.ottocontrol.IntermediateLayerBetweenModelAndViewModel
import ru.spbstu.ottocontrol.model.bluetoothconnector.BluetoothConnectorInterfaceForModel
import ru.spbstu.ottocontrol.model.bluetoothconnector.BluetoothConnector
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

class Model :
    ModelInterfaceForViewModel,
    ModelInterfaceForBluetoothConnector
{
    private val viewModel: ViewModelInterfaceForModel = IntermediateLayerBetweenModelAndViewModel


    private val pairedDevices: MutableList<BluetoothDevice> = mutableListOf()
    private val bluetoothConnector: BluetoothConnectorInterfaceForModel = BluetoothConnector(this)


    // Calls from ViewModel
    override fun initBluetooth() = bluetoothConnector.initBluetoothAdapter()
    override fun searchPairedDevices() = bluetoothConnector.searchPairedDevices()
    override fun getPairedDevices(): MutableList<BluetoothDevice> = pairedDevices


    // Calls from BluetoothConnector
    override fun addDevice(device: BluetoothDevice) {
        pairedDevices.add(device)
        viewModel.notifyViewAboutStateChange()
    }
    override fun clearListOfPairedDevices() = pairedDevices.clear()
    override fun askForTurnBluetoothOn() = viewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter)
            = viewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)


    // Demonstration
    override fun askModelFromViewModel(question: String) = viewModel.getAnswerFromModel(question + "\nHi View")
}