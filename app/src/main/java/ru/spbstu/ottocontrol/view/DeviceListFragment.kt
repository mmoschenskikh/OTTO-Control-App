package ru.spbstu.ottocontrol.view

import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.databinding.FragmentDeviceListBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment
import ru.spbstu.ottocontrol.viewmodel.DeviceListViewModel


class DeviceListFragment :
    BaseFragment<FragmentDeviceListBinding>(FragmentDeviceListBinding::inflate) {

    private val viewModel by activityViewModels<DeviceListViewModel>()

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            val device: BluetoothDevice? = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
            if (device?.bondState == BluetoothDevice.BOND_BONDED &&
                (action == BluetoothDevice.ACTION_BOND_STATE_CHANGED || action == BluetoothDevice.ACTION_ACL_CONNECTED)
            ) {
                activity?.let {
                    if (Navigation.findNavController(
                            it,
                            R.id.nav_host_fragment
                        ).currentDestination?.id == R.id.availableDevicesView
                    )
                        Navigation.findNavController(it, R.id.nav_host_fragment)
                            .navigate(R.id.action_availableDevicesView_to_controllerView)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.availabledevices_fragment, container, false)

        viewModel.closeDeviceConnection()

        val intentFilter = IntentFilter()
        intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
        activity?.registerReceiver(broadcastReceiver, intentFilter)

        val buttonUpdateList: Button = view.findViewById(R.id.updateList)
        buttonUpdateList.setOnClickListener {
            viewModel.onClickButtonUpdateList()
        }

        viewModel.showToast =
            false // it's a crutch, but otherwise the toast appear after we return to this fragment
        val toastShort = Observer<String> { message ->
            if (viewModel.showToast) Toast.makeText(
                activity,
                message,
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        val listOfPairedDevices: LinearLayout = view.findViewById(R.id.listOfPairedDevices)
        val pairedDevicesObserver = Observer<MutableList<String>> { pairedDevices ->
            listOfPairedDevices.removeAllViews()
            for (i in pairedDevices.indices) {
                val button = Button(activity)
                button.text = pairedDevices[i]
                button.setOnClickListener {
                    Toast.makeText(
                        activity,
                        "Подключение может занять несколько секунд",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.connectToPairedDevice(i)
                }
                listOfPairedDevices.addView(button)
            }
        }
        viewModel.pairedDevicesText.observe(viewLifecycleOwner, pairedDevicesObserver)

        val listOfAvailableDevices: LinearLayout = view.findViewById(R.id.listOfAvailableDevices)
        val availableDevicesObserver = Observer<MutableList<String>> { availableDevices ->
            listOfAvailableDevices.removeAllViews()
            for (i in availableDevices.indices) {
                val button = Button(activity)
                button.text = availableDevices[i]
                button.setOnClickListener {
                    Toast.makeText(
                        activity,
                        "Подключение может занять несколько секунд",
                        Toast.LENGTH_SHORT
                    ).show()
                    viewModel.connectToAvailableDevice(i)
                }
                listOfAvailableDevices.addView(button)
            }
        }
        viewModel.availableDevicesText.observe(viewLifecycleOwner, availableDevicesObserver)

        val registerReceiverObserver =
            Observer<Pair<BroadcastReceiver, IntentFilter>> { registerReceiver ->
                context?.registerReceiver(
                    registerReceiver.first,
                    registerReceiver.second
                )
            }
        viewModel.receiverRegistrar.observe(viewLifecycleOwner, registerReceiverObserver)
        viewModel.onClickButtonUpdateList()

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        if (broadcastReceiver.isOrderedBroadcast)
            activity?.unregisterReceiver(broadcastReceiver)
    }
}
