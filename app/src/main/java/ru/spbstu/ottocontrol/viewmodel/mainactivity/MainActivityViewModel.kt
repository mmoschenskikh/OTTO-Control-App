package ru.spbstu.ottocontrol.viewmodel.mainactivity

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
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


    fun askForAccessToBluetoothModule() = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
    fun askForTurnBluetoothOn() = view.toast("Включите Bluetooth", Toast.LENGTH_SHORT)
    fun initBluetooth() = model.initBluetooth()
    fun onClickButtonFindRobot() = model.searchPairedDevices()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) { (view as Context).registerReceiver(broadcastReceiver, intentFilter) }
    fun connectToDevice(index: Int) = model.connectToDevice(index)
    fun onClickLeft() = model.sendCommandToDevice("left")
    fun onClickRight() = model.sendCommandToDevice("right")
    fun onClickForward() = model.sendCommandToDevice("forward")
    fun onClickBack() = model.sendCommandToDevice("back")
    fun onClickDisconnect() = model.closeDeviceConnection()
    fun changeListOfPairedDevices() {
        availableDevicesText.clear()
        for (device in model.getPairedDevices())
            availableDevicesText.add("${device.name}; ${device.address}")
        view.showState()
    }
}