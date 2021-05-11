package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.model.Model
import ru.spbstu.ottocontrol.data.model.ModelInterface


class DeviceListViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort = MutableLiveData<String>()
    var showToast = false // crutch!
    val receiverRegistrar = MutableLiveData<Pair<BroadcastReceiver, IntentFilter>>()
    val availableDevicesText = MutableLiveData<MutableList<String>>()
    val pairedDevicesText = MutableLiveData<MutableList<String>>()

    init {
        ViewModels.deviceListViewModel = this
    }

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

    fun registerDeviceDetectionReceiver(
        broadcastReceiver: BroadcastReceiver,
        intentFilter: IntentFilter
    ) {
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
