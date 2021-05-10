package ru.spbstu.ottocontrol.view.controlpanel.options

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ru.spbstu.ottocontrol.R
import ru.spbstu.ottocontrol.viewmodel.PianoViewModel


class PianoView: Fragment() {

    class DrawPiano(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

        private val black = Paint()
        private val white = Paint()
        private val green = Paint()
        private val whiteKeys = mutableListOf<Key>()
        private val blackKeys = mutableListOf<Key>()
        private var keyWidth = 0
        private var keyHeight = 0
        private val numOfKeys = 14

        init {
            black.color = Color.BLACK
            white.color = Color.WHITE
            green.color = Color.parseColor("#4CAF50")
            black.style = Paint.Style.FILL
            white.style = Paint.Style.FILL
            green.style = Paint.Style.FILL
        }

        override fun onSizeChanged(currentWidth: Int, currentHeight: Int, oldWidth: Int, oldHeight: Int) {
            super.onSizeChanged(currentWidth, currentHeight, oldWidth, oldHeight)

            keyWidth = currentWidth / numOfKeys
            keyHeight = currentHeight
            var count = 15

            for (i in 0 until numOfKeys) {

                val left = i * keyWidth
                var right = left + keyWidth

                if (i == numOfKeys - 1) right = currentWidth

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

            val test = 0
        }

        override fun onDraw(canvas: Canvas) {

            whiteKeys.forEach { canvas.drawRect(it.rect, if (it.isPress) green else white) }

            for (i in 1 until numOfKeys) {
                val xStartStop = i * keyWidth.toFloat()
                canvas.drawLine(xStartStop, 0f, xStartStop, height.toFloat(), black)
            }

            blackKeys.forEach { canvas.drawRect(it.rect, if (it.isPress) green else black) }

        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent): Boolean {
            super.onTouchEvent(event)

            if (event.action == MotionEvent.ACTION_DOWN) {
                for (touchIndex in 0 until event.pointerCount) {
                    val x = event.getX(touchIndex)
                    val y = event.getY(touchIndex)
                    val key = keyCoordinates(x, y)
                    if (key != null) key.isPress = true
                }

                postInvalidate()

                (blackKeys + whiteKeys).forEach {
                    handler.postDelayed({
                        it.isPress = false
                        handler.sendEmptyMessage(0)
                    }, 100)
                }
            }

            return true
        }

        private fun keyCoordinates(x: Float, y: Float): Key? {

            blackKeys.forEach { if (it.rect.contains(x, y)) return it }

            whiteKeys.forEach { if (it.rect.contains(x, y)) return it }

            return null
        }

        private val handler = @SuppressLint("HandlerLeak") object : Handler() { override fun handleMessage(msg: Message) { invalidate() } }
    }

    private val viewModel: PianoViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        val view = inflater.inflate(R.layout.piano_fragment, container, false)

        val toastShort = Observer<String> {
                message -> Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        viewModel.toastShort.observe(viewLifecycleOwner, toastShort)

        return view
    }
}

class Key(var rect: RectF, var sound: Int) { var isPress = false }
