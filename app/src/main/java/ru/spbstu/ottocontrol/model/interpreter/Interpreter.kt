package ru.spbstu.ottocontrol.model.interpreter

class Interpreter {
    fun getDataToDevice(data: String): ByteArray {
        val dataToDevice = ByteArray(1024)
        when (data) {
            "left" -> dataToDevice[0] = 1
            "right" -> dataToDevice[1] = 1
            "forward" -> dataToDevice[2] = 1
            "back" -> dataToDevice[3] = 1
        }
        return dataToDevice
    }

    fun getDataFromDevice(dataFromDevice: ByteArray): String = when {
        dataFromDevice[0] == 2.toByte() -> "stepped left"
        dataFromDevice[1] == 2.toByte() -> "stepped right"
        dataFromDevice[2] == 2.toByte() -> "stepped forward"
        dataFromDevice[3] == 2.toByte() -> "stepped back"
        else -> "unknown"
    }
}