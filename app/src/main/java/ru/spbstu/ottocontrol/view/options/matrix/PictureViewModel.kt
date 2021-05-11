package ru.spbstu.ottocontrol.view.options.matrix

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.ActionRepository
import ru.spbstu.ottocontrol.data.FireMatrix
import ru.spbstu.ottocontrol.data.util.Matrix

class PictureViewModel : ViewModel() {

    private val _matrix = MutableLiveData<Set<Pair<Int, Int>>>(emptySet())

    fun onCellChanged(m: Int, n: Int) {
        // m is vertical, n is horizontal
        _matrix.value?.let { prev ->
            val pair = m to n
            _matrix.value = if (pair in prev) prev - pair else prev + pair
        }
    }

    fun onPictureDone() {
        _matrix.value?.let { set ->
            val matrix = Matrix(
                MATRIX_SIZE,
                MATRIX_SIZE,
                set
            )
            ActionRepository.sendAction(FireMatrix(matrix))
        }
    }

    companion object {
        const val MATRIX_SIZE = 8
    }
}
