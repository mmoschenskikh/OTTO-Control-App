package ru.spbstu.ottocontrol.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import butterknife.BindView
import ru.spbstu.ottocontrol.R
import butterknife.ButterKnife
import ru.spbstu.ottocontrol.viewmodel.LedViewModel
import top.defaults.colorpicker.ColorPickerView
import java.util.*

class LedView: Fragment() {

    private val savedStateKeyColor = "saved_state_key_color"
    private val initialColor = -0x8000
    private val viewModel: LedViewModel by viewModels()


    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.colorPicker) lateinit var colorPicker: ColorPickerView
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.pickedColor) lateinit var pickedColor: View
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.colorHex) lateinit var colorHex: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreate(savedInstanceState)
        val view: View = inflater.inflate(R.layout.led_fragment, container, false)
        ButterKnife.bind(this, view)

        val buttonColor: Button = view.findViewById(R.id.onClickedLedColor)

        colorPicker.subscribe { color, fromUser, shouldPropagate ->
            pickedColor.setBackgroundColor(color)
            colorHex.text = colorHex(color)
            buttonColor.setOnClickListener { viewModel.onClickLedColor(colorHex(color)) }
        }
        var color = initialColor
        if (savedInstanceState != null)
        {
            color = savedInstanceState.getInt(savedStateKeyColor, initialColor)
        }

        colorPicker.setInitialColor(color)
        return view

    }

    private fun colorHex(color: Int):String {
        val a = Color.alpha(color);
        val r = Color.red(color);
        val g = Color.green(color);
        val b = Color.blue(color);
        return String.format(Locale.getDefault(), "0x%02X%02X%02X%02X", a, r, g, b);
    }

}