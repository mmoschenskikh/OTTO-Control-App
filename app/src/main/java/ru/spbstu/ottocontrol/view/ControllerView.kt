package ru.spbstu.ottocontrol.view

import android.os.Bundle
import android.util.Log
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
import ru.spbstu.ottocontrol.viewmodel.ControllerViewModel

class ControllerView : Fragment() {
    private val viewModel: ControllerViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.controller_fragment, container, false)

        val buttonPiano: Button = view.findViewById(R.id.buttonChoosePiano)
        buttonPiano.setOnClickListener() { Navigation.findNavController(view).navigate(R.id.action_controllerView_to_pianoView) }

        val buttonLed: Button = view.findViewById(R.id.buttonChooseLed)
        buttonLed.setOnClickListener() { Navigation.findNavController(view).navigate(R.id.action_controllerView_to_ledView) }

        val buttonText: Button = view.findViewById(R.id.buttonChooseText)
        buttonText.setOnClickListener() { Navigation.findNavController(view).navigate(R.id.action_controllerView_to_textViewFragment) }

        val buttonPicture: Button = view.findViewById(R.id.buttonChoosePicture)
        buttonPicture.setOnClickListener() { Navigation.findNavController(view).navigate(R.id.action_controllerView_to_pictureView) }


        val buttonLeft: Button = view.findViewById(R.id.stepLeft)
        buttonLeft.setOnClickListener() { viewModel.onClickLeft() }

        val  buttonRight: Button = view.findViewById(R.id.stepRight)
        buttonRight.setOnClickListener() { viewModel.onClickRight() }

        val buttonForward: Button = view.findViewById(R.id.stepForward)
        buttonForward.setOnClickListener() { viewModel.onClickForward() }

        val buttonBack: Button = view.findViewById(R.id.stepBack)
        buttonBack.setOnClickListener() { viewModel.onClickBack() }

        viewModel.showToast = false
        val toastShort = Observer<String> { message -> if (viewModel.showToast) Toast.makeText(activity, message, Toast.LENGTH_SHORT).show() }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }
}