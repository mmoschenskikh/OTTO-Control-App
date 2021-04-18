package ru.spbstu.ottocontrol.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.viewmodel.TextViewModel

class TextView : Fragment()  {
    private val viewModel: TextViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.text_fragment, container, false)
}