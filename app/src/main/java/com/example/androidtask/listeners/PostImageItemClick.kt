package com.example.androidtask.listeners

import com.example.androidtask.datamodels.Hit

interface PostImageItemClick {
    fun onPostImageClick(position:Int?,postHit:Hit)
}