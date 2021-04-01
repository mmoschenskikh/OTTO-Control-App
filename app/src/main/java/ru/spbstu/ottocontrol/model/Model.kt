package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Message
import android.os.Parcelable
import android.util.Log
import ru.spbstu.ottocontrol.IntermediateLayerBetweenModelAndViewModel
import ru.spbstu.ottocontrol.model.bluetoothdeviceconnector.BluetoothDeviceConnector
import ru.spbstu.ottocontrol.model.bluetoothsearcher.BluetoothSearcher
import ru.spbstu.ottocontrol.model.interpreter.Interpreter
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel

class Model : ModelInterfaceForViewModel {
    private val viewModel: ViewModelInterfaceForModel = IntermediateLayerBetweenModelAndViewModel
    private val pairedDevices: MutableList<BluetoothDevice> = mutableListOf()
    private val bluetoothSearcher: BluetoothSearcher = BluetoothSearcher()
    private val interpreter = Interpreter()

    private lateinit var bluetoothDeviceConnector: BluetoothDeviceConnector

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == BluetoothDevice.ACTION_FOUND) {
                pairedDevices.add(intent.getParcelableExtra<Parcelable>(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice)
                viewModel.changeListOfPairedDevices()
            }
        }
    }

    // Deprecated constructor!
    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1)
                handleCommandFromRobot(msg.obj as ByteArray)
        }
    }
    private fun handleCommandFromRobot(bytes: ByteArray) = Log.i("Command from robot", interpreter.getCommandFromDevice(bytes))


    override fun initBluetooth() {
        if (!bluetoothSearcher.initBluetoothAdapter())
            viewModel.askForAccessToBluetoothModule()
    }

    override fun searchPairedDevices() {
        if (bluetoothSearcher.isDiscovering())
            bluetoothSearcher.cancelDiscovery()
        if (!bluetoothSearcher.searchPairedDevices()) {
            viewModel.askForTurnBluetoothOn()
            return
        }
        pairedDevices.clear()
        viewModel.registerDeviceDetectionReceiver(broadcastReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
    }

    override fun getPairedDevices(): MutableList<BluetoothDevice> = pairedDevices
    override fun connectToDevice(index: Int) { bluetoothDeviceConnector = BluetoothDeviceConnector(pairedDevices[index], handler) }
    override fun sendCommandToDevice(command: String) = bluetoothDeviceConnector.sendDataToDevice(interpreter.getCommandToDevice(command))
    override fun closeDeviceConnection() = bluetoothDeviceConnector.closeDeviceConnection()



    // Demonstration
    override fun askModelFromViewModel(question: String) = viewModel.getAnswerFromModel(question + "\nHi View")
}