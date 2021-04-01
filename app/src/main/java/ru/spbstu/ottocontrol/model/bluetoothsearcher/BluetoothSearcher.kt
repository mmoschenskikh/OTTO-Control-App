package ru.spbstu.ottocontrol.model.bluetoothsearcher

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Parcelable
import ru.spbstu.ottocontrol.model.ModelInterfaceForBluetoothConnector


class BluetoothSearcher(val model: ModelInterfaceForBluetoothConnector) : BluetoothSearcherInterfaceForModel  {
    private lateinit var bluetoothAdapter: BluetoothAdapter


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == BluetoothDevice.ACTION_FOUND) {
                val device = intent.getParcelableExtra<Parcelable>(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice
                model.addDevice(device)
            }
        }
    }


    // Calls from Model
    override fun initBluetoothAdapter() { bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() }
    override fun searchPairedDevices() {
        if (bluetoothAdapter.isDiscovering)
            bluetoothAdapter.cancelDiscovery()
        if (askForTurnBluetoothOn()) {
            model.clearListOfPairedDevices()
            model.registerDeviceDetectionReceiver(broadcastReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
            bluetoothAdapter.startDiscovery()
        }
    }


    private fun askForTurnBluetoothOn(): Boolean {
        if (!bluetoothAdapter.isEnabled)
            model.askForTurnBluetoothOn()
        return bluetoothAdapter.isEnabled
    }
}