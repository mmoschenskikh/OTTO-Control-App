package ru.spbstu.ottocontrol.viewmodel

import android.content.BroadcastReceiver
import android.content.IntentFilter
import ru.spbstu.ottocontrol.util.Observable
import ru.spbstu.ottocontrol.util.Observer
import ru.spbstu.ottocontrol.view.devicelist.DeviceListViewModel

object ViewModels : ViewModelInterface, Observable<ByteArray> {
    // FIXME: Actually this should be a repository

    lateinit var initViewModel: InitViewModel
    lateinit var deviceListViewModel: DeviceListViewModel

    /*
     * Observable<ByteArray> is a stub for sending data back from the
     * robot to the device. It would be more convenient to introduce
     * a separate class like [ru.spbstu.ottocontrol.data.Action] to
     * represent various types of data that might be received from the robot.
     */
    private val observers = mutableSetOf<Observer<ByteArray>>()

    override fun askForPermissionToUseBluetoothModule() {
        initViewModel.askForPermissionToUseBluetoothModule()
    }

    override fun notifyThatBluetoothIsNotSupported() =
        initViewModel.notifyThatBluetoothIsNotSupported()

    override fun askForTurnBluetoothOn() =
        deviceListViewModel.askForTurnBluetoothOn()

    override fun registerDeviceDetectionReceiver(
        broadcastReceiver: BroadcastReceiver,
        intentFilter: IntentFilter
    ) = deviceListViewModel.registerDeviceDetectionReceiver(broadcastReceiver, intentFilter)

    override fun changeListOfAvailableDevices() =
        deviceListViewModel.changeListOfAvailableDevices()

    override fun getDataFromDevice(data: ByteArray) {
        observers.forEach { it.onChange(data) }
    }

    override fun subscribe(observer: Observer<ByteArray>) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: Observer<ByteArray>) {
        observers.remove(observer)
    }
}
