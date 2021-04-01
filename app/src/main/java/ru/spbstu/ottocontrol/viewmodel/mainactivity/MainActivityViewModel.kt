package ru.spbstu.ottocontrol.viewmodel.mainactivity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.IntermediateLayerBetweenModelAndViewModel
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel
import ru.spbstu.ottocontrol.view.mainactivity.MainActivity


class MainActivityViewModel : ViewModel()  {
    // It's a bad solution
    lateinit var view: MainActivity

    val availableDevicesText: MutableList<String> = mutableListOf()

    private val model: ModelInterfaceForViewModel = IntermediateLayerBetweenModelAndViewModel


    init { IntermediateLayerBetweenModelAndViewModel.mainActivityViewModel = this }


    // Calls from View
    fun initBluetooth() = model.initBluetooth()
    fun onClickButtonFindRobot() = model.searchPairedDevices()


    // Calls from Model
    fun notifyViewAboutStateChange() {
        availableDevicesText.clear()
        for (device in model.getPairedDevices())
            availableDevicesText.add("${device.name}; ${device.address}")
        view.showState()
    }
    fun askForTurnBluetoothOn() = view.toast("Включите Bluetooth", Toast.LENGTH_SHORT)
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) {
        (view as Context).registerReceiver(broadcastReceiver, intentFilter)
    }
}