package ru.spbstu.ottocontrol.model.interpreter

class Interpreter {
    fun getDataToDevice(data: String): ByteArray = data.toByteArray()
    fun getDataFromDevice(dataFromDevice: ByteArray): String = String(dataFromDevice)
}