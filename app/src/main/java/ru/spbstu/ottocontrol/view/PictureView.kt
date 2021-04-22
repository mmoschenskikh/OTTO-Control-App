package ru.spbstu.ottocontrol.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.gridlayout.widget.GridLayout
import android.widget.Toast
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.forEach
import androidx.core.view.get
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

        val grid1: GridLayout = view.findViewById(R.id.gridLayout)
        grid1.rowCount = 8
        grid1.columnCount = 8

        for (i in 0..7) {
            for (j in 0..7) {
                val cell = TextView(context)
                cell.id = i
                cell.height = 129
                cell.width = 129
                cell.setBackgroundResource(R.drawable.cell_black)
                var count = 0
                cell.setOnClickListener {
                    if (cell.isPressed) count++
                    if (count % 2 == 1) cell.setBackgroundResource(R.drawable.cell_white)
                    else cell.setBackgroundResource(R.drawable.cell_black)
                }
                grid1.apply { grid1.addView(cell) }
            }
        }

        val grid2: GridLayout = view.findViewById(R.id.gridLayout2)
        grid2.columnCount = 2
        for (i in 0..1) {
            val button = Button(context)
            button.id = i
            button.height = 250
            button.width = 250
            if (i == 1) {
                button.setBackgroundResource(R.drawable.cell_black)
                button.setOnClickListener {
                    grid1.forEach { it.setBackgroundResource(R.drawable.cell_black) }
                }
            }
            else {
                button.setBackgroundResource(R.drawable.cell_white)
                button.setOnClickListener {
                    grid1.forEach { it.setBackgroundResource(R.drawable.cell_white) }
                }
            }
            grid2.apply { grid2.addView(button) }
        }

        val toastShort = Observer<String> {
                message -> Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }
        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }

}