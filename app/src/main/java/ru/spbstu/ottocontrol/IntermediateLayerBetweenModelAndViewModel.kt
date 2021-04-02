package ru.spbstu.ottocontrol

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.IntentFilter
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel
import ru.spbstu.ottocontrol.viewmodel.mainactivity.MainActivityViewModel
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForModel
import ru.spbstu.ottocontrol.viewmodel.somefragment.SomeFragmentViewModel

object IntermediateLayerBetweenModelAndViewModel : ViewModelInterfaceForModel, ModelInterfaceForViewModel {
    private val model: ModelInterfaceForViewModel = Model()

    lateinit var mainActivityViewModel: MainActivityViewModel


    // Calls to ViewModels from Model
    override fun askForPermissionToUseBluetoothModule() { mainActivityViewModel.askForPermissionToUseBluetoothModule() }
    override fun notifyThatBluetoothIsNotSupported() = mainActivityViewModel.notifyThatBluetoothIsNotSupported()
    override fun askForTurnBluetoothOn() = mainActivityViewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) = mainActivityViewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)
    override fun changeListOfPairedDevices() = mainActivityViewModel.changeListOfPairedDevices()
    override fun handleDataFromDevice(data: String) = mainActivityViewModel.handleDataFromRobot(data)


    // Calls to Model from ViewModels
    override fun initBluetooth() = model.initBluetooth()
    override fun searchPairedDevices() = model.searchPairedDevices()
    override fun getPairedDevices(): MutableList<BluetoothDevice> = model.getPairedDevices()
    override fun connectToDevice(index: Int) = model.connectToDevice(index)
    override fun sendDataToDevice(command: String) = model.sendDataToDevice(command)
    override fun closeDeviceConnection() = model.closeDeviceConnection()


    // Example
    lateinit var someFragmentViewModel: SomeFragmentViewModel
    override fun getAnswerFromModel(answer: String) = someFragmentViewModel.getAnswerFromModel(answer) // Call from Model
    override fun askModelFromViewModel(question: String) = model.askModelFromViewModel(question)       // Call from ViewModel
}