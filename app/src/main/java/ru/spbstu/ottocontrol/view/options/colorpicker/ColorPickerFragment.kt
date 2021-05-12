package ru.spbstu.ottocontrol.view.options.colorpicker

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.databinding.FragmentLedBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment

class ColorPickerFragment : BaseFragment<FragmentLedBinding>(FragmentLedBinding::inflate) {

    private val viewModel by activityViewModels<ColorPickerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.ledColor.observe(viewLifecycleOwner) { color ->
            binding.colorPreview.setBackgroundColor(color)
        }

        binding.colorPicker.subscribe { color, _, _ ->
            viewModel.onColorChanged(color)
        }

        val initialColor = viewModel.ledColor.value ?: 0xFFFFFF
        binding.colorPicker.setInitialColor(
            savedInstanceState?.getInt(ARG_COLOR, initialColor) ?: initialColor
        )

        binding.chooseColorButton.setOnClickListener { viewModel.onColorPicked() }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        viewModel.ledColor.value?.let { color ->
            outState.putInt(ARG_COLOR, color)
        }
    }

    companion object {
        private const val ARG_COLOR = "hex_color"
    }
}
