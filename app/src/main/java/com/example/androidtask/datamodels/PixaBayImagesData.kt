package com.example.androidtask.datamodels

data class PixaBayImagesData(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)