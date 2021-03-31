package ru.spbstu.ottocontrol.viewmodel

import ru.spbstu.ottocontrol.view.ViewInterfaceForViewModel

interface ViewModelInterfaceForView {
    val availableDevicesText: MutableList<String>
    var view: ViewInterfaceForViewModel


    fun createModel()
    fun initBluetooth()
    fun onClickButtonFindRobot()
}