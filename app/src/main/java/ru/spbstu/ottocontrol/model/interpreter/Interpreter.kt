package ru.spbstu.ottocontrol.model.interpreter

class Interpreter {
    private val sizeMatrix = 4

    fun getDataToDevice(data: String): ByteArray {
        val dataToDevice = ByteArray(sizeMatrix * sizeMatrix + 1)
        val splittedData = data.split(" ")
        when (splittedData[0]) {
            "step" -> {
                dataToDevice[0] = 0
                when (splittedData[1]) {
                    "left" -> dataToDevice[1] = 0
                    "right" -> dataToDevice[1] = 1
                    "forward" -> dataToDevice[1] = 2
                    "back" -> dataToDevice[1] = 3
                }
            }
            "led" -> {
                dataToDevice[0] = 1
                when (splittedData[1]) {
                    "#FF0000" -> dataToDevice[1] = 0
                }
            }
            "piano" -> {
                dataToDevice[0] = 2
                when (splittedData[1]) {
                    "a" -> dataToDevice[1] = 0
                }
            }
            "matrix" -> {
                dataToDevice[0] = 3
                for (i in 1..splittedData.lastIndex) {
                    val coordinate = splittedData[i].split(":")
                    dataToDevice[coordinate[0].toInt() * sizeMatrix + coordinate[1].toInt() + 1] = 1
                }
            }
            "text" -> {
                dataToDevice[0] = 4
                for (i in splittedData[1].indices)
                    dataToDevice[i + 1] = splittedData[1][i].toByte()
            }
        }
        return dataToDevice
    }

    fun getDataFromDevice(dataFromDevice: ByteArray): String {
        val builder = StringBuilder()
        when (dataFromDevice[0]) {
            0.toByte() -> {
                builder.append("step ")
                when (dataFromDevice[1]) {
                    0.toByte() -> builder.append("right ")
                    1.toByte() -> builder.append("left ")
                    2.toByte() -> builder.append("forward ")
                    3.toByte() -> builder.append("back ")
                }
            }
            1.toByte() -> {
                builder.append("led ")
                when (dataFromDevice[1]) {
                    0.toByte() -> builder.append("#FF0000 ")
                }
            }
            2.toByte() -> {
                builder.append("piano ")
                when (dataFromDevice[1]) {
                    0.toByte() -> builder.append("a ")
                }
            }
            3.toByte() -> {
                builder.append("matrix ")
                for (i in 1..dataFromDevice.lastIndex) {
                    if (dataFromDevice[i] == 1.toByte())
                        builder.append("${(i - 1) / sizeMatrix}:${(i - 1) % sizeMatrix} ")
                }
            }
            4.toByte() -> {
                builder.append("text ")
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