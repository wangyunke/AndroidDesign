package com.i.service

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.WindowManager
import android.widget.RelativeLayout
import com.i.designpattern.R
import com.i.utils.LogUtil
import kotlin.math.abs

class FloatView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val TAG = "FloatView"

    private lateinit var mWindowManager: WindowManager
    private lateinit var mParams: WindowManager.LayoutParams
    private var lastX = 0f
    private var lastY = 0f
    private var actionDownTime: Long = 0
    private var isDragAct: Boolean = false

    init {
        LayoutInflater.from(context).inflate(R.layout.float_view, this, true)
    }

    fun setManager(manager: WindowManager, params: WindowManager.LayoutParams) {
        mWindowManager = manager
        mParams = params
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return true
    }

    val FIX_PARAM_X = -440
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.rawX
                lastY = event.rawY

                actionDownTime = System.currentTimeMillis()
                isDragAct = false
            }

            MotionEvent.ACTION_MOVE -> {
                if ((System.currentTimeMillis() - actionDownTime) > 500) {
                    isDragAct = true
                    val offY = (event.rawY - lastY).toInt()
                    val offX = (event.rawX - lastX).toInt()

                    if (abs(offX) < 5 && abs(offY) < 5) {
                        return true
                    }

                    val newParamX = mParams.x + offX
                    if(newParamX < FIX_PARAM_X){
                        updateView(
                            null, null, FIX_PARAM_X, offY, null, isDragX = false, isDragY = true
                        )
                    } else {
                        updateView(
                            null, null, offX, offY, null, isDragX = true, isDragY = true
                        )
                        LogUtil.d(
                            TAG,
                            "rawX=${event.rawX.toInt()}, x=${event.x},lastX=$lastX, offX=$offX"
                        )
                    }

                    lastX = event.rawX
                    lastY = event.rawY
                    return false

                    /*var touchX = event.x
                    if(event.x < 0){
                        touchX = 0F
                    }
                    val distanceEdge = event.rawX - touchX
                    if (distanceEdge >= 420) {
                        updateView(
                            null, null, offX, offY, null, isDragX = true, isDragY = true
                        )
                    } else {
                        updateView(
                            null, null, -(420 + 20), offY, null, isDragX = false, isDragY = true
                        )
                    }*/
                }
            }

            MotionEvent.ACTION_UP -> {
                if (!isDragAct) {
                    performClick()
                }
            }
        }
        return false
    }

    private fun updateView(
        width: Int?,
        height: Int?,
        offX: Int?,
        offY: Int?,
        gravity: Int?,
        isDragX: Boolean?,
        isDragY: Boolean?
    ) {
        offX?.let {
            if (isDragX == true) {
                mParams.x += it
            } else {
                mParams.x = it
            }
        }
        offY?.let {
            if (isDragY == true) {
                mParams.y += it
            } else {
                mParams.y = it
            }
        }

        if (width != null) {
            mParams.width = width
        }
        if (height != null) {
            mParams.height = height
        }
        if (gravity != null) {
            mParams.gravity = gravity
        }
        mWindowManager.updateViewLayout(this, mParams)
        LogUtil.d(
            TAG,
            "mParams.x=${mParams.x}, mParams.y=${mParams.y}"
        )
    }

}