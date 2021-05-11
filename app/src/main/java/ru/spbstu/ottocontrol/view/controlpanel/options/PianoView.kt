package ru.spbstu.ottocontrol.view.controlpanel.options

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

class PianoView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    class Key(var rect: RectF, var sound: Int)

    var keyPressedIndex: Int? = null
        private set

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
        val height = min(heightSize.toDouble(), widthSize * (150.0 / 23 / NUMBER_OF_KEYS)).toInt()

        setMeasuredDimension(widthSize, height)
    }

    override fun onSizeChanged(
        currentWidth: Int,
        currentHeight: Int,
        oldWidth: Int,
        oldHeight: Int
    ) {
        super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight)

        keyWidth = currentWidth / NUMBER_OF_KEYS
        keyHeight = currentHeight
        var count = 15

        for (i in 0 until NUMBER_OF_KEYS) {

            val left = i * keyWidth
            var right = left + keyWidth

            if (i == NUMBER_OF_KEYS - 1) right = currentWidth

            var rect = RectF(left.toFloat(), 0f, right.toFloat(), currentHeight.toFloat())
            whiteKeys.add(Key(rect, i + 1))

            if (i != 0 && i != 3 && i != 7 && i != 10) {
                rect = RectF(
                    (i - 1).toFloat() * keyWidth + 0.5f * keyWidth + 0.25f * keyWidth,
                    0f,
                    i.toFloat() * keyWidth + 0.25f * keyWidth,
                    0.67f * height
                )
                blackKeys.add(Key(rect, count))
                count++
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        whiteKeys.forEach { key ->
            val paint = if (keyPressedIndex == key.sound - 1) green else white
            canvas.drawRect(key.rect, paint)
        }
        for (i in 1 until NUMBER_OF_KEYS) {
            val xStartStop = i * keyWidth.toFloat()
            canvas.drawLine(xStartStop, 0f, xStartStop, height.toFloat(), black)
        }
        blackKeys.forEach { key ->
            val paint = if (keyPressedIndex == key.sound - 1) green else black
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
                    keyPressedIndex = if (keyPressedIndex == key.sound - 1) null else key.sound - 1
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
        const val NUMBER_OF_KEYS = 7
        private const val BORDER_WIDTH = 8f
    }
}

