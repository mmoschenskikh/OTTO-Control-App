package ru.spbstu.ottocontrol

import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.math.min

object DeviceSocketExample {
    var dataWasRecivedFromAndroid = false
    var dataIsBeingProcessed = false
    lateinit var bytesFromAndroid: ByteArray

    object outputStream {
        fun write(bytes: ByteArray) {
            if (!dataIsBeingProcessed && !dataWasRecivedFromAndroid) {
                dataIsBeingProcessed = true
                dataWasRecivedFromAndroid = true
                bytesFromAndroid = bytes
            }
        }
    }

    object inputStream {
        fun read(bytes: ByteArray): Int {
            while (true) {
                if (!dataIsBeingProcessed && dataWasRecivedFromAndroid) {
                    val size = min(bytesFromAndroid.lastIndex, bytes.lastIndex)
                    for (i in 0..size)
                        bytes[i] = if (bytesFromAndroid[i] == 1.toByte()) 2 else bytesFromAndroid[i]
                    dataWasRecivedFromAndroid = false
                    return size
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