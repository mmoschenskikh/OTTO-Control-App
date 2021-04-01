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


    // Calls from Model
    override fun notifyViewAboutStateChange() = mainActivityViewModel.notifyViewAboutStateChange()
    override fun askForTurnBluetoothOn() = mainActivityViewModel.askForTurnBluetoothOn()
    override fun registerDeviceDetectionReceiver(broadcastReceiver: BroadcastReceiver, intentFilter: IntentFilter) =
        mainActivityViewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)


    // Calls from ViewModels
    override fun initBluetooth() = model.initBluetooth()
    override fun searchPairedDevices() = model.searchPairedDevices()
    override fun getPairedDevices(): MutableList<BluetoothDevice> = model.getPairedDevices()


    // Demonstration
    lateinit var someFragmentViewModel: SomeFragmentViewModel
    override fun getAnswerFromModel(answer: String) = model.askModelFromViewModel(answer)                       // Call from Model
    override fun askModelFromViewModel(question: String) = someFragmentViewModel.getAnswerFromModel(question)   // Call from ViewModel
}