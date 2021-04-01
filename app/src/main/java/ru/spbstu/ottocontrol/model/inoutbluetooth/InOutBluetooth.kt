package ru.spbstu.ottocontrol.model.inoutbluetooth

import android.bluetooth.BluetoothSocket
import android.os.Handler
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.concurrent.thread


class InOutBluetooth {
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null
    private lateinit var socket: BluetoothSocket
    private lateinit var thread: Thread

    fun openCommunication(socket: BluetoothSocket, handler: Handler) {
        this.socket = socket

        try {
            inputStream = socket.inputStream
            outputStream = socket.outputStream
        } catch (ex: IOException) { ex.printStackTrace() }

        thread = thread {
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
            if (!this::socket.isInitialized) {
                if (!thread.isInterrupted)
                    thread.interrupt()
                return
            }
            socket.close()
            inputStream?.close()
            outputStream?.close()
            if (this::thread.isInitialized && !thread.isInterrupted)
                thread.interrupt()
        } catch (e: IOException) { e.printStackTrace() }
    }

    fun sendDataToDevice(bytes: ByteArray) {
        try {
            outputStream!!.write(bytes)
        } catch (e: IOException) { e.printStackTrace() }
    }
}