package ru.spbstu.ottocontrol.model.bluetoothsearcher

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Parcelable


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
}