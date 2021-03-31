package ru.spbstu.ottocontrol.view

interface ViewInterfaceForViewModel {
    fun showState()
    fun toast(message: String, duration: Int)
}