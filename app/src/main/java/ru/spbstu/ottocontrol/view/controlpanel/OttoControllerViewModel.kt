package ru.spbstu.ottocontrol.view.controlpanel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.Action
import ru.spbstu.ottocontrol.data.ActionRepository
import ru.spbstu.ottocontrol.data.FireMatrix
import ru.spbstu.ottocontrol.data.util.Matrix

class OttoControllerViewModel : ViewModel() {

    private val _ledColor = MutableLiveData(0xFFFFFF)
    val ledColor: LiveData<Int> = _ledColor

    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    private val _matrix = MutableLiveData<Set<Pair<Int, Int>>>(emptySet())

    fun onAction(action: Action) {
        ActionRepository.sendAction(action)
    }

    fun onColorChanged(newColor: Int) {
        _ledColor.value = newColor
    }

    fun onColorPicked() {
        // TODO
    }

    fun onTextChanged(text: String?) {
        _text.value = text ?: ""
    }

    fun onTextEditFinished() {
        // TODO
    }

    fun onCellChanged(m: Int, n: Int) {
        // m is vertical, n is horizontal
        _matrix.value?.let { prev ->
            val pair = m to n
            _matrix.value = if (pair in prev) prev - pair else prev + pair
        }
    }

    fun onPictureDone() {
        _matrix.value?.let { set ->
            val matrix = Matrix(MATRIX_SIZE, MATRIX_SIZE, set)
            ActionRepository.sendAction(FireMatrix(matrix))
        }
    }

    companion object {
        const val MATRIX_SIZE = 8
    }
}
