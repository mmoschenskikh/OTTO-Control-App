package ru.spbstu.ottocontrol.view.options.matrix

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ToggleButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import ru.spbstu.ottocontrol.databinding.FragmentPictureBinding
import ru.spbstu.ottocontrol.view.base.BaseFragment
import ru.spbstu.ottocontrol.view.options.matrix.PictureViewModel.Companion.MATRIX_SIZE

class PictureFragment : BaseFragment<FragmentPictureBinding>(FragmentPictureBinding::inflate) {

    private val viewModel by activityViewModels<PictureViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Some magic
        val correction = ((resources.displayMetrics.widthPixels - 20) % 10) * 10
        val cellSize = (resources.displayMetrics.widthPixels - 20 + correction) / 10

        val grid = binding.gridLayout
        grid.rowCount = MATRIX_SIZE
        grid.columnCount = MATRIX_SIZE

        for (i in 0 until MATRIX_SIZE) {
            for (j in 0 until MATRIX_SIZE) {
                val layout = ConstraintLayout(requireContext())
                layout.maxHeight = cellSize
                layout.maxWidth = cellSize

                val cell = ToggleButton(requireContext())
                cell.textOff = ""
                cell.id = i * MATRIX_SIZE + j
                cell.isChecked = false
                cell.setBackgroundColor(Color.BLACK)
                cell.setOnCheckedChangeListener { buttonView, isChecked ->
                    buttonView.setBackgroundColor(
                        if (isChecked) Color.WHITE else Color.BLACK
                    )
                    viewModel.onCellChanged(i, j)
                }
                layout.addView(cell)
                grid.addView(layout)
            }
        }

        binding.makeWhiteButton.setOnClickListener {
            grid.forEach {
                it as ConstraintLayout
                val check = it[0] as ToggleButton
                check.isChecked = true
            }
        }

        binding.makeBlackButton.setOnClickListener {
            grid.forEach {
                it as ConstraintLayout
                val check = it[0] as ToggleButton
                check.isChecked = false
            }
        }

        binding.doneButton.setOnClickListener { viewModel.onPictureDone() }
    }
}
