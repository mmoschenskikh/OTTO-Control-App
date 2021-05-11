package ru.spbstu.ottocontrol.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.spbstu.ottocontrol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
