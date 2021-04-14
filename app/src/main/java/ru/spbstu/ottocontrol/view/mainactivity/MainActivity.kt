package ru.spbstu.ottocontrol.view.mainactivity

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import ru.spbstu.ottocontrol.R

import ru.spbstu.ottocontrol.viewmodel.mainactivity.MainActivityViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var listOfDevices: LinearLayout
    private lateinit var buttonFindRobot: Button
    private lateinit var buttonDisconnect: Button
    private lateinit var buttonLeft: Button
    private lateinit var buttonRight: Button
    private lateinit var buttonForward: Button
    private lateinit var buttonBack: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

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


        val pairedDevicesObserver = Observer<MutableList<String>> { pairedDevices ->
            listOfDevices.removeAllViews()
            for (i in pairedDevices.indices) {
                val button = Button(this)
                button.text = pairedDevices[i]
                button.setOnClickListener { viewModel.connectToDevice(i) }
                listOfDevices.addView(button)
            }
        }
        viewModel.pairedDevicesText.observe(this, pairedDevicesObserver)

        val toastShortObserver = Observer<String> { message -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show() }
        viewModel.toastShort.observe(this, toastShortObserver)

        val intentObserver = Observer<Pair<Intent, Int>> { intentToCode -> startActivityForResult(intentToCode.first, intentToCode.second) }
        viewModel.intent.observe(this, intentObserver)

        val registerReceiverObserver = Observer<Pair<BroadcastReceiver, IntentFilter>> { registerReceiver -> registerReceiver(registerReceiver.first, registerReceiver.second) }
        viewModel.receiverRegistrar.observe(this, registerReceiverObserver)


        viewModel.changeListOfPairedDevices()
    }
}
