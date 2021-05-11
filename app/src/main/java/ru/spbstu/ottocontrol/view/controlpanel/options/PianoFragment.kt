package ru.spbstu.ottocontrol.view.controlpanel.options

import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.databinding.FragmentPianoBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment
import ru.spbstu.ottocontrol.view.controlpanel.OttoControllerViewModel


class PianoFragment : BaseFragment<FragmentPianoBinding>(FragmentPianoBinding::inflate) {

    private val viewModel by activityViewModels<OttoControllerViewModel>()

}

