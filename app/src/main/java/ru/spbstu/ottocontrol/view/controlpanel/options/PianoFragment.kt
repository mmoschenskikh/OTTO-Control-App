package ru.spbstu.ottocontrol.view.controlpanel.options

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.databinding.FragmentPianoBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment
import ru.spbstu.ottocontrol.view.controlpanel.OttoControllerViewModel


class PianoFragment : BaseFragment<FragmentPianoBinding>(FragmentPianoBinding::inflate) {

    private val viewModel by activityViewModels<OttoControllerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.note_lengths_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }
    }
}

