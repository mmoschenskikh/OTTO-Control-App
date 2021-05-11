package ru.spbstu.ottocontrol.examples

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.spbstu.ottocontrol.util.Observer
import ru.spbstu.ottocontrol.viewmodel.ViewModels

/**
 * An example of how ViewModel can receive and handle data from the robot.
 */
class ViewModelWithRobotCallback : ViewModel(), Observer<ByteArray> {

    init {
        // First, register the ViewModel
        ViewModels.subscribe(this)
    }

    // Various ViewModel stuff here...

    private val fireMissiles = MutableLiveData(false)

    override fun onChange(data: ByteArray) {
        // Second, handle the data
        if (data[0] % 5 == 2)
            fireMissiles.value = true
    }

    override fun onCleared() {
        super.onCleared()
        // Third, do not forget to unsubscribe when the ViewModel is destroyed
        ViewModels.unsubscribe(this)
    }
}
