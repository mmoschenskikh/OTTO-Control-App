package ru.spbstu.ottocontrol.viewmodel.mainactivity

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.ModelImpl
import ru.spbstu.ottocontrol.model.ModelInterface
import ru.spbstu.ottocontrol.view.mainactivity.MainActivity
import ru.spbstu.ottocontrol.viewmodel.ViewModelImpl


class MainActivityViewModel : ViewModel()  {
    private val model: ModelInterface = ModelImpl

    val availableDevicesText: MutableList<String> = mutableListOf()
    lateinit var view: MainActivity // It's a bad solution


    init { ViewModelImpl.mainActivityViewModel = this }


    fun askForPermissionToUseBluetoothModule() = view.startActivityForResult(Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 1)
    fun notifyThatBluetoothIsNotSupported() = view.toast("Bluetooth не поддерживается", Toast.LENGTH_SHORT)
    fun askForTurnBluetoothOn() = view.toast("Включите Bluetooth", Toast.LENGTH_SHORT)
    fun initBluetooth() = model.initBluetooth()
    fun onClickButtonFindRobot() = model.searchPairedDevices()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) { (view as Context).registerReceiver(broadcastReceiver, intentFilter) }
    fun connectToDevice(index: Int) = model.connectToDevice(index)
    fun onClickLeft() = model.sendDataToDevice("left")
    fun onClickRight() = model.sendDataToDevice("right")
    fun onClickForward() = model.sendDataToDevice("forward")
    fun onClickBack() = model.sendDataToDevice("back")
    fun onClickDisconnect() = model.closeDeviceConnection()
    fun changeListOfPairedDevices() {
        availableDevicesText.clear()
        for (device in model.getPairedDevices())
            availableDevicesText.add("${device.name}; ${device.address}")
        view.showState()
    }
    fun showCommandExecutedByRobot(command: String) = view.toast(command, Toast.LENGTH_SHORT)
}