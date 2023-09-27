package com.example.androidtask.utils

import android.os.SystemClock
import android.view.View

class OnSingleClickListener(private val block: () -> Unit) : View.OnClickListener {

    companion object {
        private var lastClickTime = 0L
    }

    override fun onClick(view: View) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 600) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()

        block()
    }
}

fun View.setOnSingleClickListener(block: () -> Unit) {

    setOnClickListener(OnSingleClickListener(block))
}