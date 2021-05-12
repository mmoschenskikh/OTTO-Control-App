package ru.spbstu.ottocontrol.view.devicelist

import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ru.spbstu.ottocontrol.databinding.ItemBtDeviceBinding

// TODO: Should be used with DeviceListFragment
class DeviceListAdapter(
    private val onItemClicked: (BluetoothDevice) -> Unit
) : ListAdapter<BluetoothDevice, DeviceViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BluetoothDevice>() {
            override fun areItemsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice) =
                oldItem.address == newItem.address

            override fun areContentsTheSame(oldItem: BluetoothDevice, newItem: BluetoothDevice) =
                oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DeviceViewHolder(
            ItemBtDeviceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bind(getItem(position), onItemClicked)
    }
}
