package ru.spbstu.ottocontrol.data

sealed class Action {
    abstract fun toBytes(): ByteArray
}

// There are two sample actions below
class ShowText(val text: String) : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot show the text")
    }
}

object GoForward : Action() {
    override fun toBytes(): ByteArray {
        TODO("Return the byte sequence that makes the robot go forward")
    }
}
