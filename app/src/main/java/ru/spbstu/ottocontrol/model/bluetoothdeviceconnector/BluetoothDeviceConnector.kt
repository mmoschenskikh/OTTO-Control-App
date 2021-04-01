package ru.spbstu.ottocontrol.model.bluetoothdeviceconnector

import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.os.Handler
import ru.spbstu.ottocontrol.model.inoutbluetooth.InOutBluetooth
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import kotlin.concurrent.thread

class BluetoothDeviceConnector(val device: BluetoothDevice, val handler: Handler) {
    private lateinit var socket: BluetoothSocket
    private var inOutBluetooth: InOutBluetooth? = null

    init {
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
            inOutBluetooth = InOutBluetooth(socket, handler)
        }
    }

    fun closeDeviceConnection() {
        try {
            socket.close()
            inOutBluetooth?.closeCommunication()
        } catch (e: IOException) { e.printStackTrace() }
    }

    fun sendDataToDevice(bytes: ByteArray) = inOutBluetooth!!.sendDataToDevice(bytes)
}