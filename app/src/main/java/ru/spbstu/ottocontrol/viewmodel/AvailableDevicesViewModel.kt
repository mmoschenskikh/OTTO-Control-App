package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterface


class AvailableDevicesViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()
    var showToast = false // crutch!
    val receiverRegistrar: MutableLiveData<Pair<BroadcastReceiver, IntentFilter>> = MutableLiveData()
    val availableDevicesText: MutableLiveData<MutableList<String>> = MutableLiveData()
    val pairedDevicesText: MutableLiveData<MutableList<String>> = MutableLiveData()

    init { ViewModels.availableDevicesViewModel = this }

    fun connectToPairedDevice(index: Int) {
        model.connectToPairedDevice(index)
    }
    fun connectToAvailableDevice(index: Int) {
        model.connectToAvailableDevice(index)
    }
    fun askForTurnBluetoothOn() {
        showToast = true // crutch!
        toastShort.value = "Включите Bluetooth"
    }
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) {
        receiverRegistrar.value = broadcastReceiver to intentFilter
    }
    fun onClickButtonUpdateList() {
        model.searchAvailableDevices()
        val devices = mutableListOf<String>()
        for (device in model.getListOfPairedDevices())
            devices.add("${device.name}; ${device.address}")
        pairedDevicesText.value = devices
    }
    fun closeDeviceConnection() {
        model.closeDeviceConnection()
    }

    fun changeListOfAvailableDevices() {
        val devices = mutableListOf<String>()
        for (device in model.getListOfAvailableDevices()) {
            val name = if (device.name == null) "Нет имени" else device.name
            devices.add("$name; ${device.address}")
        }
        availableDevicesText.value = devices
    }
}