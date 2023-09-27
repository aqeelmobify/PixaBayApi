package com.example.androidtask.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtask.enums.RequestState
import com.example.androidtask.repositries.PixaBayImagesDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repositoryFramesCollection: PixaBayImagesDataRepository,
) : ViewModel() {
    val requestState = MutableLiveData<RequestState>()
    val pixBayImages get() = repositoryFramesCollection.imagesData
    fun getPixaBayImagesData() {
        Log.e("MainActivityViewModel","getPixaBayImagesData")
        repositoryFramesCollection.getPixaBayImages(requestState)
    }
}