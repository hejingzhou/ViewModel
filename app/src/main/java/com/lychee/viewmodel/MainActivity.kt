package com.lychee.viewmodel

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity(), TimeViewModel.OnTimeChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //这个mTimeViewModel对象无论屏幕怎么翻转（onCreate方法走多少次）都是一个  生命周期为Activity开始到Activity结束
        val mTimeViewModel = ViewModelProvider(this).get(TimeViewModel::class.java)
        mTimeViewModel.setOnTimeChangeListener(this)
        mTimeViewModel.startTime()
    }

    override fun onTimeChanged(second: Long) {
        //更新UI界面
        runOnUiThread { findViewById<TextView>(R.id.mTvTime).text = "TIME:$second" }
    }
}
