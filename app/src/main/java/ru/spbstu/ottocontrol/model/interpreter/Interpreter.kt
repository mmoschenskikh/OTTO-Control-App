package ru.spbstu.ottocontrol.model.interpreter

import java.lang.Exception

class Interpreter {
    private val sizeMatrix = 4

    private val commandsStrToByte = mapOf<String, Byte>("step" to 0, "led" to 1, "piano" to 2, "matrix" to 3, "text" to 4)
    private val stepsStrToByte = mapOf<String, Byte>("left" to 0, "right" to 1, "forward" to 2, "back" to 3)
    private val ledStrToByte = mapOf<String, Byte>("#FF0000" to 0, "#FFFFFF" to 1, "#000000" to 2)
    private val pianoStrToByte = mapOf<String, Byte>("a" to 0, "b" to 1, "c" to 2, "d" to 3)

    fun getDataToDevice(data: String): ByteArray {
        val dataToDevice = ByteArray(sizeMatrix * sizeMatrix + 1)
        val splittedData = data.split(" ")
        val command = splittedData[0]
        dataToDevice[0] = commandsStrToByte[command] ?: throw Exception("Unknown command")
        when (command) {
            "step" -> dataToDevice[1] = stepsStrToByte[splittedData[1]] ?: throw Exception("Unknown command")
            "led" -> dataToDevice[1] = ledStrToByte[splittedData[1]] ?: throw Exception("Unknown command")
            "piano" -> dataToDevice[1] = pianoStrToByte[splittedData[1]] ?: throw Exception("Unknown command")
            "matrix" -> {
                for (i in 1..splittedData.lastIndex) {
                    val coordinate = splittedData[i].split(":")
                    dataToDevice[coordinate[0].toInt() * sizeMatrix + coordinate[1].toInt() + 1] = 1
                }
            }
            "text" -> {
                for (i in splittedData[1].indices)
                    dataToDevice[i + 1] = splittedData[1][i].toByte()
            }
        }
        return dataToDevice
    }

    private val commandsByteToStr = commandsStrToByte.entries.associate{(k, v) -> v to k}
    private val stepsStrByteToStr = stepsStrToByte.entries.associate{(k, v) -> v to k}
    private val ledStrByteToStr = ledStrToByte.entries.associate{(k, v) -> v to k}
    private val pianoStrByteToStr = pianoStrToByte.entries.associate{(k, v) -> v to k}

    fun getDataFromDevice(dataFromDevice: ByteArray): String {
        val builder = StringBuilder()
        val command = commandsByteToStr[dataFromDevice[0]]
        builder.append("$command ")
        when (command) {
            "step" -> builder.append("${stepsStrByteToStr[dataFromDevice[1]]} ")
            "led" -> builder.append("${ledStrByteToStr[dataFromDevice[1]]} ")
            "piano" -> builder.append("${pianoStrByteToStr[dataFromDevice[1]]} ")
            "matrix" -> {
                for (i in 1..dataFromDevice.lastIndex) {
                    if (dataFromDevice[i] == 1.toByte())
                        builder.append("${(i - 1) / sizeMatrix}:${(i - 1) % sizeMatrix} ")
                }
            }
            "text" -> {
                for (i in 1..dataFromDevice.lastIndex)
                    if (dataFromDevice[i] != 0.toByte())
                        builder.append(("${dataFromDevice[i].toChar()}"))
                builder.append(" ")
            }
        }
        builder.append("(from the robot)")
        return builder.toString()
    }
}