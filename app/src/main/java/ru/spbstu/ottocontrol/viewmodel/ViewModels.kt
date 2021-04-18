package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter

object ViewModels : ViewModelInterface {
    lateinit var initViewModel: InitViewModel
    lateinit var availableDevicesViewModel: AvailableDevicesViewModel
    lateinit var controllerViewModel: ControllerViewModel
    
    override fun askForPermissionToUseBluetoothModule() { initViewModel.askForPermissionToUseBluetoothModule() }
    override fun notifyThatBluetoothIsNotSupported() = initViewModel.notifyThatBluetoothIsNotSupported()
    override fun askForTurnBluetoothOn() = availableDevicesViewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) = availableDevicesViewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)
    override fun changeListOfPairedDevices() = availableDevicesViewModel.changeListOfPairedDevices()
    override fun getDataFromDevice(data: String) {
        val splittedData = data.split(' ')
        if (splittedData.size == 2 && splittedData[0] == "stepped")
            controllerViewModel.showCommandExecutedByRobot(data)
    }
}