package ru.spbstu.ottocontrol

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Parcelable
import android.widget.Toast


class BluetoothConnector(val viewModel: MainActivityViewModel)  {
    lateinit var bluetoothAdapter: BluetoothAdapter


    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == BluetoothDevice.ACTION_FOUND) {
                val device = intent.getParcelableExtra<Parcelable>(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice
                viewModel.availableDevices.add("${device.name}; ${device.address}")
                viewModel.notifyViewAboutStateChange()
            }
        }
    }


    fun initBluetooth() { bluetoothAdapter = BluetoothAdapter.getDefaultAdapter() }

    fun searchAvailableDevices() {
        if (bluetoothAdapter.isDiscovering) bluetoothAdapter.cancelDiscovery()

        if (askForTurnBluetoothOn()) {
            viewModel.availableDevices.clear()

            viewModel.view.registerReceiver(mReceiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
            bluetoothAdapter.startDiscovery()
        }
    }

    private fun askForTurnBluetoothOn(): Boolean {
        if (!bluetoothAdapter.isEnabled) Toast.makeText(viewModel.view, "Включите Bluetooth", Toast.LENGTH_SHORT).show()
        return bluetoothAdapter.isEnabled
    }
}