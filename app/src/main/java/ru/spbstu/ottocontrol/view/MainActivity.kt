package ru.spbstu.ottocontrol.view

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.spbstu.ottocontrol.R

import ru.spbstu.ottocontrol.viewmodel.MainActivityViewModel
import ru.spbstu.ottocontrol.viewmodel.ViewModelInterfaceForView


class MainActivity : AppCompatActivity(), ViewInterfaceForViewModel {
    private lateinit var viewModel: ViewModelInterfaceForView
    private lateinit var listOfDevices: LinearLayout
    private lateinit var buttonFindRobot: Button

    private var init = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!init) {
            init = true

            setContentView(R.layout.activity_main)
            viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
            viewModel.view = this
            viewModel.createModel()

            viewModel.initBluetooth()

            buttonFindRobot = findViewById(R.id.findRobot)
            buttonFindRobot.setOnClickListener() { viewModel.onClickButtonFindRobot() }

            listOfDevices = findViewById(R.id.listOfDevices)
        }

        showState()
    }

    // Calls from ViewModel
    override fun showState() {
        listOfDevices.removeAllViews()
        for (deviceText in viewModel.availableDevicesText) {
            val button = Button(this)
            button.text = deviceText
            listOfDevices.addView(button)
        }
    }
    override fun toast(message: String, duration: Int) = Toast.makeText(this, message, duration).show()
}
