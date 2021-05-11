package ru.spbstu.ottocontrol.data

import ru.spbstu.ottocontrol.data.util.Matrix

sealed class Action {
    abstract fun toBytes(): ByteArray
}

class ShowText(val text: String) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot show the text")
    }
}

class FireLed(val color: Int) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot fire the LED")
    }
}

class PlaySound(val pitch: Int) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot play the sound")
    }
}

class FireMatrix(val matrix: Matrix) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot show the picture")
    }
}

object GoForward : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot go forward")
    }
}

object GoBack : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot go back")
    }
}

object GoLeft : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot turn left")
    }
}

object GoRight : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot turn right")
    }
}
