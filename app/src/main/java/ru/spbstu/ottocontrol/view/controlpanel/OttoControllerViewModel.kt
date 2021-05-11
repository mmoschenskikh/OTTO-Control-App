package ru.spbstu.ottocontrol.view.controlpanel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.*
import ru.spbstu.ottocontrol.data.util.Matrix
import ru.spbstu.ottocontrol.data.util.Sound
import ru.spbstu.ottocontrol.data.util.parseFraction

class OttoControllerViewModel : ViewModel() {

    private val _ledColor = MutableLiveData(0xFFFFFF)
    val ledColor: LiveData<Int> = _ledColor

    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    private val _matrix = MutableLiveData<Set<Pair<Int, Int>>>(emptySet())

    private val _sound = MutableLiveData<Sound?>(null)
    val sound: LiveData<Sound?> = _sound

    fun onAction(action: Action) {
        ActionRepository.sendAction(action)
    }

    fun onColorChanged(newColor: Int) {
        _ledColor.value = newColor
    }

    fun onColorPicked() {
        _ledColor.value?.let { color ->
            ActionRepository.sendAction(FireLed(color))
        }
    }

    fun onTextChanged(text: String?) {
        _text.value = text ?: ""
    }

    fun onTextEditFinished() {
        _text.value?.let { string ->
            ActionRepository.sendAction(ShowText(string))
        }
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

    fun onKeyClicked(index: Int?) {
        _sound.value = Sound(index, sound.value?.length)
    }

    fun onLengthChanged(length: String) {
        val time = length.parseFraction()
        _sound.value = Sound(sound.value?.pitch, time)
    }

    fun onSoundPicked() {
        sound.value?.let {
            ActionRepository.sendAction(PlaySound(it))
        }
    }

    companion object {
        const val MATRIX_SIZE = 8
    }
}
