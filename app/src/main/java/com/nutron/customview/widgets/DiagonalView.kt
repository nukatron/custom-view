package com.nutron.customview.widgets

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.nutron.customview.R


class DiagonalView : View {

    private var lineColor: Int = 0xFFFF0000.toInt()
    private val paint = Paint()

    constructor(context: Context?) : super(context) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
        initWithAttrs(attrs, 0, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
        initWithAttrs(attrs, defStyleAttr, 0)
    }
    @TargetApi(21)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
        initWithAttrs(attrs, defStyleAttr, defStyleRes)
    }

    private fun init() {

    }

    private fun initWithAttrs(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.DiagonalView, defStyleAttr, defStyleRes)
        try {
            lineColor = a.getColor(R.styleable.DiagonalView_color, 0xFFFF0000.toInt())
        } finally {
            a.recycle()
        }

        paint.color = lineColor
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f,0f, measuredWidth.toFloat(), measuredHeight.toFloat(), paint)
    }


}