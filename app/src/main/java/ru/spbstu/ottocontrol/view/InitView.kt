package ru.spbstu.ottocontrol.view


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.viewmodel.InitViewModel

class InitView : Fragment() {
    // FIXME: Useless Fragment, everything should be moved to the device list
    private val viewModel: InitViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.init_fragment, container, false)

        viewModel.initBluetooth()

        val buttonShowList: Button = view.findViewById(R.id.showList)
        buttonShowList.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_initView_to_availableDevicesView)
        }

        val intentPermissionToUseBluetooth = Observer<Pair<Intent, Int>> { intentToCode ->
            startActivityForResult(
                intentToCode.first,
                intentToCode.second
            )
        }
        viewModel.intentPermissionToUseBluetooth.observe(
            viewLifecycleOwner,
            intentPermissionToUseBluetooth
        )

        val toastShort = Observer<String> { message ->
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }
}
