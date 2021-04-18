package ru.spbstu.ottocontrol.view

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
import ru.spbstu.ottocontrol.viewmodel.PianoViewModel

class PianoView : Fragment()  {
    private val viewModel: PianoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.piano_fragment, container, false)

        val sendPianoA: Button = view.findViewById(R.id.sendPianoA)
        sendPianoA.setOnClickListener { viewModel.onClickPianoA() }

        val toastShort = Observer<String> { message -> Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }
}