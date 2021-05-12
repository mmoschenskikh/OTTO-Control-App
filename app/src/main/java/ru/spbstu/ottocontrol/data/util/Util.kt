package ru.spbstu.ottocontrol.data.util

fun String.parseFraction(): Double? =
    if (this.contains('/')) {
        val parts = this.split('/')
        parts[0].toDouble() / parts[1].toDouble()
    } else {
        this.toDoubleOrNull()
    }
