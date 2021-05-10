package ru.spbstu.ottocontrol.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.gridlayout.widget.GridLayout
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.viewmodel.PictureViewModel

class PictureView : Fragment()  {

    private val viewModel: PictureViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        val view = inflater.inflate(R.layout.picture_fragment, container, false)


        val grid: GridLayout = view.findViewById(R.id.gridLayout)
        grid.rowCount = 8
        grid.columnCount = 8
        grid.useDefaultMargins = true

        val correction = ((resources.displayMetrics.widthPixels - 20) % 10) * 10
        val cellSize = (resources.displayMetrics.widthPixels - 20 + correction) / 10

        for (i in 0..7) {
            for (j in 0..7) {
                val layout = ConstraintLayout(requireContext())
                layout.maxHeight = cellSize
                layout.maxWidth = cellSize
                val cell = ToggleButton(context)
                cell.textOff = ""
                cell.id = i
                cell.isChecked = true
                cell.setBackgroundColor(Color.BLACK)
                cell.setOnClickListener {
                    if (!cell.isChecked) {
                        viewModel.onClickSetWhite(i, j)

                        cell.setBackgroundColor(Color.WHITE)
                        cell.isChecked = false
                    }
                    else {
                        viewModel.onClickSetBlack(i, j)

                        cell.setBackgroundColor(Color.BLACK)
                        cell.isChecked = true
                    }
                }
                layout.addView(cell)
                grid.addView(layout)
            }
        }

        val button1: Button = view.findViewById(R.id.button1)
        button1.setBackgroundColor(Color.WHITE)
        button1.setOnClickListener {
            viewModel.onClickSetWhite()

            grid.forEach {
                (it as ConstraintLayout)[0].setBackgroundColor(Color.WHITE)
                val check = it[0] as ToggleButton
                if (check.isChecked) check.isChecked = !check.isChecked
            }
        }

        val button2: Button = view.findViewById(R.id.button2)
        button2.setBackgroundColor(Color.BLACK)
        button2.setOnClickListener {
            viewModel.onClickSetBlack()

            grid.forEach {
                (it as ConstraintLayout)[0].setBackgroundColor(Color.BLACK)
                val check = it[0] as ToggleButton
                if (!check.isChecked) check.isChecked = !check.isChecked
            }
        }

        val toastShort = Observer<String> {
                message -> Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }
}