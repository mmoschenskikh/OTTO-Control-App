package ru.spbstu.ottocontrol.view.options.text

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.databinding.FragmentTextBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment

class TextFragment : BaseFragment<FragmentTextBinding>(FragmentTextBinding::inflate) {

    private val viewModel by activityViewModels<TextViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textEditDoneButton.setOnClickListener { viewModel.onTextEditFinished() }

        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit

            override fun afterTextChanged(s: Editable?) {
                viewModel.onTextChanged(s?.toString())
            }
        })

        viewModel.text.observe(viewLifecycleOwner) {
            binding.textEditDoneButton.isEnabled = !it.isNullOrBlank()
        }
    }
}
