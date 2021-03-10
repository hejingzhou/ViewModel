package com.lychee.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*

/**
 * Company:看大医(北京)科技发展有限公司
 *
 * Date:3/10/21 9:53 AM
 *
 * Author:HeJingzhou
 *
 * Email:tcoywork@163.com
 *
 * Description:
 */
class TimeViewModel : ViewModel() {

    private var currentTime: Long = 0
    private var timer: Timer? = null

    override fun onCleared() {
        super.onCleared()
        Log.i("HJZ", "onCleared: ")
        timer?.cancel()
    }

    /**
     * 开始计数
     */
    fun startTime() {
        if (timer == null) {
            currentTime = 0
            timer = Timer()

            val timerTask = object : TimerTask() {
                override fun run() {
                    currentTime++
                    onTimeChangeListener?.onTimeChanged(currentTime)
                }
            }
            timer?.schedule(timerTask, 1000, 1000)
        }
    }

    /**
     * 通过接口的方式，完成对调用者的通知，这种方式不是太好，更好的方式是通过LiveData组件来实现
     */
    interface OnTimeChangeListener {
        fun onTimeChanged(second: Long)
    }

    private var onTimeChangeListener: OnTimeChangeListener? = null

    fun setOnTimeChangeListener(onTimeChangeListener: OnTimeChangeListener?) {
        this.onTimeChangeListener = onTimeChangeListener
    }
}