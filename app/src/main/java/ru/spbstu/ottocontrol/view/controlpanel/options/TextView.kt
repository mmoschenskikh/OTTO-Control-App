package ru.spbstu.ottocontrol.view.controlpanel.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.viewmodel.TextViewModel

class TextView : Fragment()  {
    private val viewModel: TextViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.text_fragment, container, false)

        val sendTextABC: Button = view.findViewById(R.id.sendTextABC)
        sendTextABC.setOnClickListener { viewModel.onClickTextABC() }

        val toastShort = Observer<String> { message -> Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }
}
