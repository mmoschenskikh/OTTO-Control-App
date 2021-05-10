package ru.spbstu.ottocontrol.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.databinding.FragmentLedBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment
import ru.spbstu.ottocontrol.viewmodel.LedViewModel
import java.util.*

class ColorPickerFragment : BaseFragment<FragmentLedBinding>(FragmentLedBinding::inflate) {

    private val initialColor = 0xFFFFFF
    private val viewModel by activityViewModels<LedViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonColor = binding.chooseColorButton
        val colorPicker = binding.colorPicker
        val pickedColor = binding.colorPreview

        colorPicker.subscribe { color, _, _ ->
            pickedColor.setBackgroundColor(color)
            buttonColor.setOnClickListener { viewModel.onClickLedColor(colorHex(color)) }
        }

        val color = savedInstanceState?.getInt(ARG_COLOR, initialColor) ?: initialColor
        colorPicker.setInitialColor(color)
    }

    private fun colorHex(color: Int): String {
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        return String.format(Locale.getDefault(), "0x%02X%02X%02X", r, g, b)
    }

    companion object {
        private const val ARG_COLOR = "hex_color"
    }
}
