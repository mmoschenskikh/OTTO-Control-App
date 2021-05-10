package ru.spbstu.ottocontrol.ui.devicelist

import android.bluetooth.BluetoothDevice
import androidx.recyclerview.widget.RecyclerView
import ru.spbstu.ottocontrol.databinding.ItemBtDeviceBinding

class DeviceViewHolder(private val binding: ItemBtDeviceBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(device: BluetoothDevice, onItemClicked: (BluetoothDevice) -> Unit) {
        TODO()
    }
}
