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
    var showToast = false
    val receiverRegistrar: MutableLiveData<Pair<BroadcastReceiver, IntentFilter>> = MutableLiveData()
    val pairedDevicesText: MutableLiveData<MutableList<String>> = MutableLiveData()

    init { ViewModels.availableDevicesViewModel = this }

    fun connectToDevice(index: Int) = model.connectToDevice(index)

    fun askForTurnBluetoothOn() {
        showToast = true
        toastShort.value = "Включите Bluetooth"
    }
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) { receiverRegistrar.value = broadcastReceiver to intentFilter }
    fun onClickButtonUpdateList() = model.searchPairedDevices()
    fun changeListOfPairedDevices() {
        val devices = mutableListOf<String>()
        for (device in model.getPairedDevices())
            devices.add("${device.name}; ${device.address}")
        pairedDevicesText.value = devices
    }
}