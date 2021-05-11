package ru.spbstu.ottocontrol.data

import ru.spbstu.ottocontrol.data.util.Matrix
import ru.spbstu.ottocontrol.data.util.Sound

sealed class Action {
    abstract fun toBytes(): ByteArray
}

class ShowText(private val text: String) : Action() {
    override fun toBytes(): ByteArray {
        return "The text is: \"${text}\"".toByteArray()
    }
}

class FireLed(private val color: Int) : Action() {
    override fun toBytes(): ByteArray {
        return "Firing LED with $color".toByteArray()
    }
}

class PlaySound(private val sound: Sound) : Action() {
    override fun toBytes(): ByteArray {
        return "Playing sound of pitch ${sound.pitch} and duration ${sound.length} seconds".toByteArray()
    }
}

class FireMatrix(private val matrix: Matrix) : Action() {
    override fun toBytes(): ByteArray {
        return "Firing the following cells: ${matrix.content}".toByteArray()
    }
}

object GoForward : Action() {
    override fun toBytes(): ByteArray {
        return "Going forward...".toByteArray()
    }
}

object GoBack : Action() {
    override fun toBytes(): ByteArray {
        return "Going backward...".toByteArray()
    }
}

object GoLeft : Action() {
    override fun toBytes(): ByteArray {
        return "Turning left...".toByteArray()
    }
}

object GoRight : Action() {
    override fun toBytes(): ByteArray {
        return "Turning right...".toByteArray()
    }
}
