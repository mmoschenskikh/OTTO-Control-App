package ru.spbstu.ottocontrol.view.options.piano

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.ActionRepository
import ru.spbstu.ottocontrol.data.PlaySound
import ru.spbstu.ottocontrol.data.util.Sound
import ru.spbstu.ottocontrol.data.util.parseFraction
import ru.spbstu.ottocontrol.viewmodel.ViewModels

class PianoViewModel : ViewModel() {

    init {
        ViewModels.pianoViewModel = this
    }

    private val _sound = MutableLiveData<Sound?>(null)
    val sound: LiveData<Sound?> = _sound

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
}
