package ru.spbstu.ottocontrol

import androidx.lifecycle.ViewModel


class MainActivityViewModel : ViewModel() {
    lateinit var view: MainActivity

    val availableDevices = mutableListOf<String>()

    private val bluetoothConnector = BluetoothConnector(this)


    fun notifyViewAboutStateChange() = view.showState()

    fun initBluetooth() = bluetoothConnector.initBluetooth()

    fun onClickButtonFindRobot() = bluetoothConnector.searchAvailableDevices()
}