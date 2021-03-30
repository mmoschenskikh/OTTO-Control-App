package ru.spbstu.ottocontrol

import android.bluetooth.BluetoothAdapter
import android.widget.Toast


class BluetoothConnector(val viewModel: MainActivityViewModel)  {
    lateinit var bluetoothAdapter: BluetoothAdapter


    fun initBluetooth() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
    }

    fun searchAvailableDevices() {
        viewModel.availableDevices.clear()

        if (askForTurnBluetoothOn()) {
            bluetoothAdapter.startDiscovery()

            // Добавляются только ранее подключенные устройства
            // for (device in bluetoothAdapter.bondedDevices)
            //     viewModel.availableDevices.add(device.name + "; " + device.address)

            // Заглушка:
            val numberOfDevices = (1..10).random()
            for (i in 1..numberOfDevices) viewModel.availableDevices.add("device $i")

            viewModel.notifyViewAboutStateChange()
        }
    }

    private fun askForTurnBluetoothOn(): Boolean {
        if (!bluetoothAdapter.isEnabled) Toast.makeText(viewModel.view, "Включите Bluetooth", Toast.LENGTH_SHORT).show()
        return bluetoothAdapter.isEnabled
    }
}