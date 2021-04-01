package ru.spbstu.ottocontrol.model.interpreter

class Interpreter {
    fun getCommandToDevice(command: String): ByteArray {
        val commandToRobot = ByteArray(1024)
        when (command) {
            "left" -> commandToRobot[0] = 1
            "right" -> commandToRobot[1] = 1
            "forward" -> commandToRobot[2] = 1
            "back" -> commandToRobot[3] = 1
        }
        return commandToRobot
    }

    fun getCommandFromDevice(commandFromRobot: ByteArray): String = when {
        commandFromRobot[0] == 1.toByte() -> "stepped left"
        commandFromRobot[1] == 1.toByte() -> "stepped right"
        commandFromRobot[2] == 1.toByte() -> "stepped forward"
        commandFromRobot[3] == 1.toByte() -> "stepped back"
        else -> "unknown"
    }
}