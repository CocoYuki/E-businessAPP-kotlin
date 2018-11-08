package com.yirong.core.utils.timer

import java.util.TimerTask

/**
 * Created by yiron on 2018/11/8.
 */
class BaseTimerTask(timerListener: ITimerListener) : TimerTask() {
    private var mITimeListener: ITimerListener? = null

    init {
        this.mITimeListener = timerListener
    }

    override fun run() {
        if (mITimeListener != null) {
            mITimeListener!!.onTimer()
        }
    }
}
