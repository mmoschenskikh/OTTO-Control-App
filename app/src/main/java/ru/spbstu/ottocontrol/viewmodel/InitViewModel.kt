package ru.spbstu.ottocontrol.viewmodel

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.model.Model
import ru.spbstu.ottocontrol.data.model.ModelInterface

class InitViewModel : ViewModel() {
    private val model: ModelInterface = Model
    val toastShort: MutableLiveData<String> = MutableLiveData()
    val intentPermissionToUseBluetooth: MutableLiveData<Pair<Intent, Int>> = MutableLiveData()

    init {
        ViewModels.initViewModel = this
    }

    fun initBluetooth() = model.initBluetooth()

    fun askForPermissionToUseBluetoothModule() {
        intentPermissionToUseBluetooth.value = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE) to 1
    }

    // FIXME: Extract string to string resources
    fun notifyThatBluetoothIsNotSupported() {
        toastShort.value = "Bluetooth не поддерживается"
    }
}
