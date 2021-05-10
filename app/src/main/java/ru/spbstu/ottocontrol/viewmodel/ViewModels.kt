package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter

object ViewModels : ViewModelInterface {
    lateinit var initViewModel: InitViewModel
    lateinit var availableDevicesViewModel: AvailableDevicesViewModel
    lateinit var controllerViewModel: ControllerViewModel
    lateinit var ledViewModel: LedViewModel
    lateinit var pianoViewModel: PianoViewModel
    lateinit var textViewModel: TextViewModel
    lateinit var pictureViewModel: PictureViewModel

    override fun askForPermissionToUseBluetoothModule() { initViewModel.askForPermissionToUseBluetoothModule() }
    override fun notifyThatBluetoothIsNotSupported() = initViewModel.notifyThatBluetoothIsNotSupported()
    override fun notifyThatChosenDeviceIsNotAvailable() = availableDevicesViewModel.notifyThatChosenDeviceIsNotAvailable()
    override fun askForTurnBluetoothOn() = availableDevicesViewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) = availableDevicesViewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)
    override fun changeListOfAvailableDevices() = availableDevicesViewModel.changeListOfAvailableDevices()

    override fun getDataFromDevice(data: String) {
        val splittedData = data.split(' ')
        if (splittedData.isNotEmpty()) {
            when (splittedData[0]) {
                "step" -> controllerViewModel.showCommandExecutedByRobot(data)
                "led" -> ledViewModel.showCommandExecutedByRobot(data)
                "piano" -> pianoViewModel.showCommandExecutedByRobot(data)
                "text" -> textViewModel.showCommandExecutedByRobot(data)
                "matrix" -> pictureViewModel.showCommandExecutedByRobot(data)
            }
        }
    }
}