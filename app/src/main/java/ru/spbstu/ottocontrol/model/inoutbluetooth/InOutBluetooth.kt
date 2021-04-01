package ru.spbstu.ottocontrol.model.inoutbluetooth

import android.bluetooth.BluetoothSocket
import android.os.Handler
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.concurrent.thread


class InOutBluetooth(val socket: BluetoothSocket, val handler: Handler) {
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null

    init {
        try {
            inputStream = socket.inputStream
            outputStream = socket.outputStream
        } catch (ex: IOException) { ex.printStackTrace() }

        thread {
            val buffer = ByteArray(1024)
            while (true) {
                try {
                    val bytes = inputStream!!.read(buffer)
                    handler.sendMessage(handler.obtainMessage(1, bytes, -1, buffer))
                } catch (e: IOException) { e.printStackTrace() }
            }
        }
    }

    fun closeCommunication() {
        try {
            socket.close()
            inputStream?.close()
            outputStream?.close()
        } catch (e: IOException) { e.printStackTrace() }
    }

    fun sendDataToDevice(bytes: ByteArray) {
        try {
            outputStream!!.write(bytes)
        } catch (e: IOException) { e.printStackTrace() }
    }
}