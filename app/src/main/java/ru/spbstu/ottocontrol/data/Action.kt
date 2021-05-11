package ru.spbstu.ottocontrol.data

import ru.spbstu.ottocontrol.data.util.Matrix
import ru.spbstu.ottocontrol.data.util.Sound

sealed class Action {
    abstract fun toBytes(): ByteArray
}

class ShowText(private val text: String) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot show the text")
    }

    override fun toString(): String { // FIXME: Only for testing
        return "ShowText(\"${text}\")"
    }
}

class FireLed(private val color: Int) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot fire the LED")
    }

    override fun toString(): String { // FIXME: Only for testing
        return "FireLed(${color})"
    }
}

class PlaySound(private val sound: Sound) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot play the sound")
    }

    override fun toString(): String { // FIXME: Only for testing
        return "PlaySound(${sound})"
    }
}

class FireMatrix(private val matrix: Matrix) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot show the picture")
    }

    override fun toString(): String { // FIXME: Only for testing
        return "FireMatrix($matrix)"
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
