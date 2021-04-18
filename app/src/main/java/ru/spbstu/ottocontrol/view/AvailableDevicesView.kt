package ru.spbstu.ottocontrol.view

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.viewmodel.AvailableDevicesViewModel

class AvailableDevicesView : Fragment() {
    private val viewModel: AvailableDevicesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.availabledevices_fragment, container, false)

        val buttonUpdateList: Button = view.findViewById(R.id.updateList)
        buttonUpdateList.setOnClickListener {
            viewModel.onClickButtonUpdateList()
        }

        viewModel.showToast = false
        val toastShort = Observer<String> { message -> if (viewModel.showToast) Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        val listOfDevices: LinearLayout = view.findViewById(R.id.listOfDevices)
        val pairedDevicesObserver = Observer<MutableList<String>> { pairedDevices ->
            listOfDevices.removeAllViews()
            for (i in pairedDevices.indices) {
                val button = Button(activity)
                button.text = pairedDevices[i]
                button.setOnClickListener {
                    viewModel.connectToDevice(i)
                    Navigation.findNavController(view).navigate(R.id.action_availableDevicesView_to_controllerView)
                }
                listOfDevices.addView(button)
            }
        }
        viewModel.pairedDevicesText.observe(viewLifecycleOwner, pairedDevicesObserver)

        val registerReceiverObserver = Observer<Pair<BroadcastReceiver, IntentFilter>> { registerReceiver -> context?.registerReceiver(registerReceiver.first, registerReceiver.second) }
        viewModel.receiverRegistrar.observe(viewLifecycleOwner, registerReceiverObserver)
        viewModel.onClickButtonUpdateList()

        return view
    }
}