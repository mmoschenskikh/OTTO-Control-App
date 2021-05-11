package ru.spbstu.ottocontrol.view.controlpanel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.data.GoBack
import ru.spbstu.ottocontrol.data.GoForward
import ru.spbstu.ottocontrol.data.GoLeft
import ru.spbstu.ottocontrol.data.GoRight
import ru.spbstu.ottocontrol.databinding.FragmentOttoControllerBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment

class OttoControllerFragment :
    BaseFragment<FragmentOttoControllerBinding>(FragmentOttoControllerBinding::inflate) {

    private val viewModel by activityViewModels<OttoControllerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            soundButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_pianoView) }
            ledButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_ledView) }
            textButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_textViewFragment) }
            matrixButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_pictureView) }
            forwardButton.setOnClickListener { viewModel.onAction(GoForward) }
            backButton.setOnClickListener { viewModel.onAction(GoBack) }
            leftButton.setOnClickListener { viewModel.onAction(GoLeft) }
            rightButton.setOnClickListener { viewModel.onAction(GoRight) }
        }
    }
}
