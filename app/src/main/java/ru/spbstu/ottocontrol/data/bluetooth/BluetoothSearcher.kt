package ru.spbstu.ottocontrol.data.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice


class BluetoothSearcher  {
    private lateinit var bluetoothAdapter: BluetoothAdapter


    fun initBluetoothAdapter(): Boolean {
        val bAdapter = BluetoothAdapter.getDefaultAdapter()
        return if (bAdapter != null) {
            bluetoothAdapter = bAdapter
            true
        } else false
    }

    fun isDiscovering() = bluetoothAdapter.isDiscovering
    fun cancelDiscovery() = bluetoothAdapter.cancelDiscovery()

    fun searchPairedDevices(): Boolean {
        if (!bluetoothAdapter.isEnabled)
            return false
        bluetoothAdapter.startDiscovery()
        return true
    }

    fun getBondedDevices(): MutableSet<BluetoothDevice> = bluetoothAdapter.bondedDevices
}
