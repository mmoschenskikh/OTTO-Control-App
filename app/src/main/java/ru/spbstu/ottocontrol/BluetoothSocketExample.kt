package ru.spbstu.ottocontrol

import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.math.min

object BluetoothSocketExample {
    var dataWasReceivedFromAndroid = false
    var dataIsBeingProcessed = false
    lateinit var bytesFromAndroid: ByteArray

    object outputStream {
        fun write(bytes: ByteArray) {
            if (!dataIsBeingProcessed && !dataWasReceivedFromAndroid) {
                dataIsBeingProcessed = true
                dataWasReceivedFromAndroid = true
                bytesFromAndroid = bytes
            }
        }
    }

    object inputStream {
        fun read(bytes: ByteArray): Int {
            while (true) {
                if (!dataIsBeingProcessed && dataWasReceivedFromAndroid) {
                    val lastIndex = min(bytesFromAndroid.lastIndex, bytes.lastIndex)
                    for (i in 0..lastIndex)
                        bytes[i] = if (bytesFromAndroid[i] == 1.toByte()) 2 else bytesFromAndroid[i]
                    dataWasReceivedFromAndroid = false
                    return lastIndex + 1
                }
            }
        }
    }

    init {
        thread {
            while (true) {
                if (dataIsBeingProcessed) {
                    val timeToProcessData = (1000..2000).random()
                    sleep(timeToProcessData.toLong())
                    dataIsBeingProcessed = false
                }
            }
        }
    }
}