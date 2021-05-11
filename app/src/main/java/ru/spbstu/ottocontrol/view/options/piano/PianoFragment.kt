package ru.spbstu.ottocontrol.view.options.piano

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.data.util.isNotNull
import ru.spbstu.ottocontrol.databinding.FragmentPianoBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment

class PianoFragment : BaseFragment<FragmentPianoBinding>(FragmentPianoBinding::inflate) {

    private val viewModel by activityViewModels<PianoViewModel>()

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
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                val item = parent.getItemAtPosition(pos) as String
                viewModel.onLengthChanged(item)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }

        binding.pianoView.subscribe(object : KeyIndexObserver {
            override fun onChange(newKeyIndex: Int?) {
                viewModel.onKeyClicked(newKeyIndex)
            }
        })

        binding.playNoteButton.setOnClickListener { viewModel.onSoundPicked() }

        viewModel.sound.observe(viewLifecycleOwner) { sound ->
            binding.playNoteButton.isEnabled = sound.isNotNull()
        }
    }
}

