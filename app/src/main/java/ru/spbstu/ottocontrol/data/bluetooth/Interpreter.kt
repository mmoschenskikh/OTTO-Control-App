package ru.spbstu.ottocontrol.data.bluetooth

class Interpreter {
    fun getDataToDevice(data: String): ByteArray = data.toByteArray()
    fun getDataFromDevice(dataFromDevice: ByteArray): String = String(dataFromDevice)
}
