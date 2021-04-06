package ru.spbstu.ottocontrol.view.mainactivity

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.spbstu.ottocontrol.R

import ru.spbstu.ottocontrol.viewmodel.mainactivity.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var listOfDevices: LinearLayout
    private lateinit var buttonFindRobot: Button
    private lateinit var buttonDisconnect: Button
    private lateinit var buttonLeft: Button
    private lateinit var buttonRight: Button
    private lateinit var buttonForward: Button
    private lateinit var buttonBack: Button

    private var init = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!init) {
            init = true

            setContentView(R.layout.activity_main)
            viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

            // It's a bad solution
            viewModel.view = this

            viewModel.initBluetooth()


            buttonFindRobot = findViewById(R.id.findRobot)
            buttonFindRobot.setOnClickListener() { viewModel.onClickButtonFindRobot() }

            buttonDisconnect = findViewById(R.id.disconnect)
            buttonDisconnect.setOnClickListener() { viewModel.onClickDisconnect() }

            buttonLeft = findViewById(R.id.left)
            buttonLeft.setOnClickListener() { viewModel.onClickLeft() }

            buttonRight = findViewById(R.id.right)
            buttonRight.setOnClickListener() { viewModel.onClickRight() }

            buttonForward = findViewById(R.id.forward)
            buttonForward.setOnClickListener() { viewModel.onClickForward() }

            buttonBack = findViewById(R.id.back)
            buttonBack.setOnClickListener() { viewModel.onClickBack() }


            listOfDevices = findViewById(R.id.listOfDevices)
        }

        showState()
    }


    fun showState() {
        listOfDevices.removeAllViews()
        for (i in viewModel.availableDevicesText.indices) {
            val button = Button(this)
            button.text = viewModel.availableDevicesText[i]
            button.setOnClickListener { viewModel.connectToDevice(i) }
            listOfDevices.addView(button)
        }
    }
    fun toast(message: String, duration: Int) = Toast.makeText(this, message, duration).show()
}
