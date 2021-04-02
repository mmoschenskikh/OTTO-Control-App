package ru.spbstu.ottocontrol.model.interpreter

class Interpreter {
    fun getCommandToDevice(command: String): ByteArray {
        val commandToDevice = ByteArray(1024)
        when (command) {
            "left" -> commandToDevice[0] = 1
            "right" -> commandToDevice[1] = 1
            "forward" -> commandToDevice[2] = 1
            "back" -> commandToDevice[3] = 1
        }
        return commandToDevice
    }

    fun getCommandFromDevice(commandFromDevice: ByteArray): String = when {
        commandFromDevice[0] == 1.toByte() -> "stepped left"
        commandFromDevice[1] == 1.toByte() -> "stepped right"
        commandFromDevice[2] == 1.toByte() -> "stepped forward"
        commandFromDevice[3] == 1.toByte() -> "stepped back"
        else -> "unknown"
    }
}