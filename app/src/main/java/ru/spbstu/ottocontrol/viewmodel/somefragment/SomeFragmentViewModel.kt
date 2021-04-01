package ru.spbstu.ottocontrol.viewmodel.somefragment

import android.util.Log
import ru.spbstu.ottocontrol.IntermediateLayerBetweenModelAndViewModel
import ru.spbstu.ottocontrol.model.ModelInterfaceForViewModel

class SomeFragmentViewModel {
    private val model: ModelInterfaceForViewModel = IntermediateLayerBetweenModelAndViewModel

    init { IntermediateLayerBetweenModelAndViewModel.someFragmentViewModel = this  }

    fun askModel() {
        val question = "Hi Model"
        Log.i("Ask:\n", question)
        model.askModelFromViewModel(question)
    }

    fun getAnswerFromModel(answer: String) { Log.i("Answer:\n", answer) }
}