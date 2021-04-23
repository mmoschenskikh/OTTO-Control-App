package ru.spbstu.ottocontrol.ui.controlpanel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.data.Action
import ru.spbstu.ottocontrol.data.GoForward
import ru.spbstu.ottocontrol.databinding.FragmentOttoControlPanelBinding
import ru.spbstu.ottocontrol.ui.base.BaseFragment

class OttoControlPanelFragment :
    BaseFragment<FragmentOttoControlPanelBinding>(FragmentOttoControlPanelBinding::inflate) {

    private val viewModel: OttoControlPanelViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.forwardButton.setOnClickListener {
            onActionClicked(GoForward)
        }
    }

    private fun onActionClicked(action: Action) {
        viewModel.onActionClicked(action)
    }
}
