package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel
import ru.spbstu.ottocontrol.view.ViewInterfaceForViewModel


class MainActivityViewModel : ViewModel(), ViewModelInterfaceForView, ViewModelInterfaceForModel {
    override val availableDevicesText: MutableList<String> = mutableListOf()
    override lateinit var model: ModelInterfaceForViewModel
    override lateinit var view: ViewInterfaceForViewModel


    // Calls from View
    override fun createModel() { model = Model(this)}
    override fun initBluetooth() = model.initBluetooth()
    override fun onClickButtonFindRobot() = model.searchAvailableDevices()

    // Calls from Model
    override fun notifyViewAboutStateChange() {
        availableDevicesText.clear()
        for (device in model.availableDevices)
            availableDevicesText.add("${device.name}; ${device.address}")
        view.showState()
    }
    override fun askForTurnBluetoothOn() = view.toast("Включите Bluetooth", Toast.LENGTH_SHORT)
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) {
        (view as Context).registerReceiver(broadcastReceiver, intentFilter)
    }
}