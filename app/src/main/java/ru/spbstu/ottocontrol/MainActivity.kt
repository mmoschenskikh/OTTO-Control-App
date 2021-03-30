package ru.spbstu.ottocontrol

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainActivityViewModel
    lateinit var listOfDevices: LinearLayout
    lateinit var buttonFindRobot: Button

    var init = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!init) {
            init = true

            setContentView(R.layout.activity_main)
            viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            viewModel.view = this

            viewModel.initBluetooth()

            buttonFindRobot = findViewById(R.id.findRobot)
            buttonFindRobot.setOnClickListener() { viewModel.onClickButtonFindRobot() }

            listOfDevices = findViewById(R.id.listOfDevices)
        }

        showState()
    }

    fun showState() {
        listOfDevices.removeAllViews()
        for (item in viewModel.availableDevices) {
            val button = Button(this)
            button.text = item
            listOfDevices.addView(button)
        }
    }
}
