package ru.spbstu.ottocontrol.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.model.Model
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel
import ru.spbstu.ottocontrol.view.ViewInterfaceForViewModel


class MainActivityViewModel : ViewModel(), ViewModelInterfaceForView, ViewModelInterfaceForModel {
    override val availableDevicesText: MutableList<String> = mutableListOf()
    override lateinit var model: ModelInterfaceForViewModel
    override lateinit var view: ViewInterfaceForViewModel


    override fun createModel() { model = Model(this)}
    override fun initBluetooth() = model.initBluetooth()
    override fun onClickButtonFindRobot() = model.searchAvailableDevices()

    // Model
    override fun notifyViewAboutStateChange() {
        availableDevicesText.clear()
        for (device in model.availableDevices)
            availableDevicesText.add("${device.name}; ${device.address}")
        view.showState()
    }
    override fun getContext(): Context = view as Context
}