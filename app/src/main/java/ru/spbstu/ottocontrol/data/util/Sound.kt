package ru.spbstu.ottocontrol.data.util

/**
 * Class representing a sound to be made by the robot.
 * Fields might be null so null check needed.
 *
 * @param [pitch] is value from 0 to [ru.spbstu.ottocontrol.view.controlpanel.options.PianoView.KEYS_COUNT].
 * The lowest C is 0, C# is 1, D is 2 etc.
 * @param [length] is the time value of the sound measured in seconds.
 */
data class Sound(val pitch: Int?, val length: Double?)

fun Sound?.isNotNull(): Boolean = this != null && pitch != null && length != null