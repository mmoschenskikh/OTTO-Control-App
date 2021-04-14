package ru.spbstu.ottocontrol.viewmodel.mainactivity

import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.ModelImpl
import ru.spbstu.ottocontrol.model.ModelInterface
import ru.spbstu.ottocontrol.viewmodel.ViewModelImpl


class MainActivityViewModel : ViewModel()  {
    private val model: ModelInterface = ModelImpl

    val pairedDevicesText: MutableLiveData<MutableList<String>> = MutableLiveData()
    val toastShort: MutableLiveData<String> = MutableLiveData()
    val intent: MutableLiveData<Pair<Intent, Int>> = MutableLiveData()
    val receiverRegistrar: MutableLiveData<Pair<BroadcastReceiver, IntentFilter>> = MutableLiveData()


    init { ViewModelImpl.mainActivityViewModel = this }


    fun askForPermissionToUseBluetoothModule() { intent.value = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE) to 1 }
    fun notifyThatBluetoothIsNotSupported() { toastShort.value = "Bluetooth не поддерживается" }
    fun askForTurnBluetoothOn() { toastShort.value = "Включите Bluetooth" }
    fun initBluetooth() = model.initBluetooth()
    fun onClickButtonFindRobot() = model.searchPairedDevices()
    fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) { receiverRegistrar.value = broadcastReceiver to intentFilter }
    fun connectToDevice(index: Int) = model.connectToDevice(index)
    fun onClickLeft() = model.sendDataToDevice("left")
    fun onClickRight() = model.sendDataToDevice("right")
    fun onClickForward() = model.sendDataToDevice("forward")
    fun onClickBack() = model.sendDataToDevice("back")
    fun onClickDisconnect() = model.closeDeviceConnection()
    fun changeListOfPairedDevices() {
        val devices = mutableListOf<String>()
        for (device in model.getPairedDevices())
            devices.add("${device.name}; ${device.address}")
        pairedDevicesText.value = devices
    }
    fun showCommandExecutedByRobot(command: String) { toastShort.value = command }
}