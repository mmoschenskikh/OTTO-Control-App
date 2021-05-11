package ru.spbstu.ottocontrol.view.options.piano

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import ru.spbstu.ottocontrol.R
import kotlin.math.min

class PianoView(context: Context?, attrs: AttributeSet?) :
    View(context, attrs), KeyIndexObservable {

    private class Key(var rect: RectF, var sound: Int)

    private var keyPressedIndex: Int? = null
        set(value) {
            if (value == null || value in 0 until KEYS_COUNT) {
                field = value
            } else {
                throw IndexOutOfBoundsException("Wrong key index $value when the piano contains ${KEYS_COUNT + 1} keys")
            }
        }

    private val observers = mutableSetOf<KeyIndexObserver>()

    private val black = Paint().also {
        it.color = Color.BLACK
        it.style = Paint.Style.FILL
    }
    private val white = Paint().also {
        it.color = Color.WHITE
        it.style = Paint.Style.FILL
    }
    private val green = Paint().also { paint ->
        paint.color =
            context?.let { ContextCompat.getColor(it, R.color.green_otto_1) } ?: Color.GREEN
        paint.style = Paint.Style.FILL
    }
    private val border = Paint().also {
        it.color = Color.BLACK
        it.strokeWidth = BORDER_WIDTH
        it.style = Paint.Style.STROKE
    }

    private val whiteKeys = mutableListOf<Key>()
    private val blackKeys = mutableListOf<Key>()
    private var keyWidth = 0
    private var keyHeight = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = min(heightSize.toDouble(), widthSize * (150.0 / 23 / WHITE_KEYS_COUNT)).toInt()

        setMeasuredDimension(widthSize, height)
    }

    override fun onSizeChanged(
        currentWidth: Int,
        currentHeight: Int,
        oldWidth: Int,
        oldHeight: Int
    ) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight)

        keyWidth = currentWidth / WHITE_KEYS_COUNT
        keyHeight = currentHeight

        var index = 0
        for (i in 0 until WHITE_KEYS_COUNT) {
            val left = i * keyWidth
            val right =
                if (i == KEYS_COUNT - 1)
                    currentWidth
                else
                    left + keyWidth

            var rect = RectF(left.toFloat(), 0f, right.toFloat(), currentHeight.toFloat())
            whiteKeys.add(Key(rect, index))
            index++

            if (i % 7 != 0 && i % 7 != 3) {
                rect = RectF(
                    (i - 1f) * keyWidth + 0.5f * keyWidth + 0.25f * keyWidth,
                    0f,
                    left.toFloat() + 0.25f * keyWidth,
                    0.67f * height
                )
                blackKeys.add(Key(rect, index))
                index++
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        whiteKeys.forEach { key ->
            val paint = if (keyPressedIndex == key.sound) green else white
            canvas.drawRect(key.rect, paint)
        }
        for (i in 1 until WHITE_KEYS_COUNT) {
            val xStartStop = i * keyWidth.toFloat()
            canvas.drawLine(xStartStop, 0f, xStartStop, height.toFloat(), black)
        }
        blackKeys.forEach { key ->
            val paint = if (keyPressedIndex == key.sound) green else black
            canvas.drawRect(key.rect, paint)
        }
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), border)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        super.onTouchEvent(event)

        if (event.action == MotionEvent.ACTION_DOWN) {
            for (touchIndex in 0 until event.pointerCount) {
                val x = event.getX(touchIndex)
                val y = event.getY(touchIndex)
                val key = keyCoordinates(x, y)
                if (key != null) {
                    keyPressedIndex = if (keyPressedIndex == key.sound) null else key.sound
                    observers.forEach { it.onChange(keyPressedIndex) }
                }
            }

            postInvalidate()
        }

        return true
    }

    private fun keyCoordinates(x: Float, y: Float): Key? {
        blackKeys.forEach { if (it.rect.contains(x, y)) return it }
        whiteKeys.forEach { if (it.rect.contains(x, y)) return it }
        return null
    }

    companion object {
        const val OCTAVES = 1
        const val WHITE_KEYS_COUNT = 7 * OCTAVES
        const val KEYS_COUNT = 12 * OCTAVES
        private const val BORDER_WIDTH = 8f
    }

    override fun subscribe(observer: KeyIndexObserver) {
        observers.add(observer)
    }

    override fun unsubscribe(observer: KeyIndexObserver) {
        observers.remove(observer)
    }
}

interface KeyIndexObservable {
    fun subscribe(observer: KeyIndexObserver)
    fun unsubscribe(observer: KeyIndexObserver)
}

interface KeyIndexObserver {
    fun onChange(newKeyIndex: Int?)
}
