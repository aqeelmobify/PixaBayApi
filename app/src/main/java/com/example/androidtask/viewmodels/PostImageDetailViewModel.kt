package com.example.androidtask.viewmodels

import androidx.lifecycle.ViewModel
import com.example.androidtask.datamodels.Hit
import dagger.hilt.android.lifecycle.HiltViewModel

class PostImageDetailViewModel :ViewModel() {

    var selectedImagePost:Hit?=null
}