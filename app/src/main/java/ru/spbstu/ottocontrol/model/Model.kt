package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.IntentFilter
import ru.spbstu.ottocontrol.IntermediateLayerBetweenModelAndViewModel
import ru.spbstu.ottocontrol.model.bluetoothsearcher.BluetoothSearcherInterfaceForModel
import ru.spbstu.ottocontrol.model.bluetoothsearcher.BluetoothSearcher
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

class Model :
    ModelInterfaceForViewModel,
    ModelInterfaceForBluetoothConnector
{
    private val viewModel: ViewModelInterfaceForModel = IntermediateLayerBetweenModelAndViewModel


    private val pairedDevices: MutableList<BluetoothDevice> = mutableListOf()
    private val bluetoothSearcher: BluetoothSearcherInterfaceForModel = BluetoothSearcher(this)


    // Calls from ViewModel
    override fun initBluetooth() = bluetoothSearcher.initBluetoothAdapter()
    override fun searchPairedDevices() = bluetoothSearcher.searchPairedDevices()
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