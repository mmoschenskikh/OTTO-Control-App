package ru.spbstu.ottocontrol.viewmodel.somefragment

import android.util.Log
import ru.spbstu.ottocontrol.model.ModelImpl
import ru.spbstu.ottocontrol.model.ModelInterface
import ru.spbstu.ottocontrol.viewmodel.ViewModelImpl

class SomeFragmentViewModel {
    private val model: ModelInterface = ModelImpl


    init { ViewModelImpl.someFragmentViewModel = this }


    fun askModel() {
        val question = "Hi Model"
        Log.i("Ask:\n", question)
        model.askModelFromViewModel(question)
    }

    fun getAnswerFromModel(answer: String) { Log.i("Answer:\n", answer) }
}