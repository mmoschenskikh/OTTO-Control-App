package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter
import ru.spbstu.ottocontrol.viewmodel.mainactivity.MainActivityViewModel
import ru.spbstu.ottocontrol.viewmodel.somefragment.SomeFragmentViewModel

object ViewModelImpl : ViewModelInterface {
    lateinit var mainActivityViewModel: MainActivityViewModel
    
    override fun askForPermissionToUseBluetoothModule() { mainActivityViewModel.askForPermissionToUseBluetoothModule() }
    override fun notifyThatBluetoothIsNotSupported() = mainActivityViewModel.notifyThatBluetoothIsNotSupported()
    override fun askForTurnBluetoothOn() = mainActivityViewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) = mainActivityViewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)
    override fun changeListOfPairedDevices() = mainActivityViewModel.changeListOfPairedDevices()
    override fun getDataFromDevice(data: String) {
        val splittedData = data.split(' ')
        if (splittedData.size == 2 && splittedData[0] == "stepped")
            mainActivityViewModel.showCommandExecutedByRobot(data)
    }

    // Example
    lateinit var someFragmentViewModel: SomeFragmentViewModel
    override fun getAnswerFromModel(answer: String) = someFragmentViewModel.getAnswerFromModel(answer) // Call from Model
}