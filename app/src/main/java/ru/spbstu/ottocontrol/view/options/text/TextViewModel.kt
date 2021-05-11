package ru.spbstu.ottocontrol.view.options.text

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.data.ActionRepository
import ru.spbstu.ottocontrol.data.ShowText

class TextViewModel : ViewModel() {

    private val _text = MutableLiveData("")
    val text: LiveData<String> = _text

    fun onTextChanged(text: String?) {
        _text.value = text ?: ""
    }

    fun onTextEditFinished() {
        _text.value?.let { string ->
            ActionRepository.sendAction(ShowText(string))
        }
    }
}
