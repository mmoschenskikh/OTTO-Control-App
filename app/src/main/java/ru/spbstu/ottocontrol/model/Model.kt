package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.os.Parcelable
import ru.spbstu.ottocontrol.model.bluetoothdeviceconnector.BluetoothDeviceConnector
import ru.spbstu.ottocontrol.model.bluetoothsearcher.BluetoothSearcher
import ru.spbstu.ottocontrol.model.interpreter.Interpreter
import ru.spbstu.ottocontrol.viewmodel.ViewModels
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterface

object Model : ModelInterface {
    private val viewModel: ViewModelInterface = ViewModels

    private val CODE_RECEIVED_DATA_FROM_DEVICE = 1
    private lateinit var pairedDevices: List<BluetoothDevice>
    private val availableDevices = mutableListOf<BluetoothDevice>()
    private val bluetoothSearcher = BluetoothSearcher()
    private val bluetoothDeviceConnector = BluetoothDeviceConnector(CODE_RECEIVED_DATA_FROM_DEVICE)
    private val interpreter = Interpreter()

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == BluetoothDevice.ACTION_FOUND) {
                val device = intent.getParcelableExtra<Parcelable>(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice
                if (!availableDevices.contains(device) && device.name != null) {
                    availableDevices.add(device)
                    viewModel.changeListOfAvailableDevices()
                }
            }
        }
    }

    private val handler = object : Handler(Looper.myLooper()!!) {
        override fun handleMessage(msg: Message) {
            if (msg.what == CODE_RECEIVED_DATA_FROM_DEVICE)
                handleDataFromDevice(msg.obj as ByteArray)
        }
    }
    private fun handleDataFromDevice(bytes: ByteArray) = viewModel.getDataFromDevice(interpreter.getDataFromDevice(bytes))


    override fun initBluetooth() {
        if (!bluetoothSearcher.initBluetoothAdapter())
            viewModel.notifyThatBluetoothIsNotSupported()
        else
            viewModel.askForPermissionToUseBluetoothModule()
    }

    override fun searchAvailableDevices() {
        availableDevices.clear()
        if (bluetoothSearcher.isDiscovering())
            bluetoothSearcher.cancelDiscovery()
        if (!bluetoothSearcher.searchPairedDevices()) {
            viewModel.askForTurnBluetoothOn()
            return
        }
        viewModel.changeListOfAvailableDevices()
        viewModel.registerDeviceDetectionReceiver(broadcastReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
    }

    override fun getListOfAvailableDevices(): List<BluetoothDevice> = availableDevices
    override fun getListOfPairedDevices(): List<BluetoothDevice> {
        pairedDevices = bluetoothSearcher.getBondedDevices().toList()
        return pairedDevices
    }
    override fun connectToPairedDevice(index: Int) {
        if (!availableDevices.contains(pairedDevices[index]))
            viewModel.notifyThatChosenDeviceIsNotAvailable()
        else
            bluetoothDeviceConnector.openDeviceConnection(pairedDevices[index], handler)
    }
    override fun connectToAvailableDevice(index: Int) = bluetoothDeviceConnector.openDeviceConnection(availableDevices[index], handler)
    override fun sendDataToDevice(data: String) = bluetoothDeviceConnector.sendDataToDevice(interpreter.getDataToDevice(data))
    override fun closeDeviceConnection() = bluetoothDeviceConnector.closeDeviceConnection()
}