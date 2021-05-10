package ru.spbstu.ottocontrol.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.databinding.FragmentOttoControllerBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment
import ru.spbstu.ottocontrol.viewmodel.ControllerViewModel

class OttoControllerFragment :
    BaseFragment<FragmentOttoControllerBinding>(FragmentOttoControllerBinding::inflate) {

    private val viewModel by activityViewModels<ControllerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        with(binding) {
            soundButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_pianoView) }
            ledButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_ledView) }
            textButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_textViewFragment) }
            matrixButton.setOnClickListener { findNavController().navigate(R.id.action_controllerView_to_pictureView) }
            forwardButton.setOnClickListener { viewModel.onClickForward() }
            backButton.setOnClickListener { viewModel.onClickBack() }
            leftButton.setOnClickListener { viewModel.onClickLeft() }
            rightButton.setOnClickListener { viewModel.onClickRight() }
        }

        viewModel.showToast = false
        viewModel.toastShort.observe(viewLifecycleOwner, { message ->
            if (viewModel.showToast)
                Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
        })
    }
}
