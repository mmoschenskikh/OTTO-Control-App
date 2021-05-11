package ru.spbstu.ottocontrol.data.util

/**
 * Class representing a picture displayed by the robot.
 * It is expected that a matrix cell has only two states (i.e. black and white).
 *
 * @param [height] height of the picture.
 * @param [width] width of the picture.
 * @param [content] represents the set of coordinates of lightened pixels.
 */
data class Matrix(val height: Int, val width: Int, val content: Set<Pair<Int, Int>>)
