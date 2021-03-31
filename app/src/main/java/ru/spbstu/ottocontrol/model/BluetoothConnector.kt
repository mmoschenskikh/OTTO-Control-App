package ru.spbstu.ottocontrol.model

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Parcelable
import android.widget.Toast


class BluetoothConnector(val model: Model)  {
    lateinit var bluetoothAdapter: BluetoothAdapter


    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == BluetoothDevice.ACTION_FOUND) {
                val device = intent.getParcelableExtra<Parcelable>(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice
                model.addDevice(device)
            }
        }
    }


    fun initBluetooth() { bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() }

    fun searchAvailableDevices() {
        if (bluetoothAdapter.isDiscovering)
            bluetoothAdapter.cancelDiscovery()

        if (askForTurnBluetoothOn()) {
            model.clearListOfDevices()

            model.getContext().registerReceiver(mReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
            bluetoothAdapter.startDiscovery()
        }
    }

    private fun askForTurnBluetoothOn(): Boolean {
        if (!bluetoothAdapter.isEnabled)
            Toast.makeText(model.getContext(), "Включите Bluetooth", Toast.LENGTH_SHORT).show()
        return bluetoothAdapter.isEnabled
    }
}