package ru.spbstu.ottocontrol.model.inoutbluetooth

import android.bluetooth.BluetoothSocket
import android.os.Handler
import ru.spbstu.ottocontrol.BluetoothSocketExample
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import kotlin.concurrent.thread


class InOutBluetooth(val CODE_RECEIVED_DATA_FROM_DEVICE: Int) {
    private var inputStream: InputStream? = null
    private var outputStream: OutputStream? = null
    private lateinit var socket: BluetoothSocket
    private lateinit var thread: Thread
    private val bufferSize = 64

    fun openCommunication(socket: BluetoothSocket, handler: Handler) {
        this.socket = socket

        try {
            inputStream = socket.inputStream
            outputStream = socket.outputStream
        } catch (ex: IOException) { ex.printStackTrace() }

        thread = thread {
            var buffer = ByteArray(bufferSize)
            while (true) {
                try {
                    // EXAMPLE: instead of this:
                    //val bytes = inputStream!!.read(buffer)
                    // we use:
                    val bytes = BluetoothSocketExample.inputStream.read(buffer)

                    handler.sendMessage(handler.obtainMessage(CODE_RECEIVED_DATA_FROM_DEVICE, bytes, -1, buffer))
                    buffer = ByteArray(bufferSize)
                } catch (e: IOException) { e.printStackTrace() }
            }
        }
    }

    fun closeCommunication() {
        if (!this::socket.isInitialized)
            return
        try {
            socket.close()
            inputStream?.close()
            outputStream?.close()
            if (this::thread.isInitialized && !thread.isInterrupted)
                thread.interrupt()
        } catch (e: IOException) { e.printStackTrace() }
    }

    fun sendDataToDevice(bytes: ByteArray) {
        try {
            // EXAMPLE: instead of this:
            //outputStream!!.write(bytes)
            // we use:
            BluetoothSocketExample.outputStream.write(bytes)
        } catch (e: IOException) { e.printStackTrace() }
    }
}