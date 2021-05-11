package ru.spbstu.ottocontrol.data.bluetooth

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Handler
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import kotlin.concurrent.thread

class BluetoothDeviceConnector(CODE_RECEIVED_DATA_FROM_DEVICE: Int) {
    private val inOutBluetooth = InOutBluetooth(CODE_RECEIVED_DATA_FROM_DEVICE)
    private lateinit var socket: BluetoothSocket

    fun openDeviceConnection(device: BluetoothDevice, handler: Handler) {
        try {
            val method = device.javaClass.getMethod("createRfcommSocket", Int::class.javaPrimitiveType)
            socket = method.invoke(device, 1) as BluetoothSocket
        }
        catch (e: NoSuchMethodException) { e.printStackTrace() }
        catch (e: InvocationTargetException) { e.printStackTrace() }
        catch (e: IllegalAccessException) { e.printStackTrace() }

        thread {
            try {
                socket.connect()
            } catch (e: IOException) {
                e.printStackTrace()
                try {
                    socket.close()
                } catch (ex: IOException) { ex.printStackTrace() }
                return@thread
            }
            if (socket.isConnected)
                inOutBluetooth.openCommunication(socket, handler)
        }
    }

    fun closeDeviceConnection() {
        if (!this::socket.isInitialized)
            return
        try {
            inOutBluetooth.closeCommunication()
        } catch (e: IOException) { e.printStackTrace() }
    }

    fun sendDataToDevice(bytes: ByteArray) = inOutBluetooth.let {
        if (!this::socket.isInitialized || !socket.isConnected)
            return@let
        it.sendDataToDevice(bytes)
    }
}
