package com.example.androidtask.repositries

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidtask.datamodels.PixaBayImagesData
import com.example.androidtask.enums.RequestState
import com.example.androidtask.network.retrofitInterfaces.RetrofitServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixaBayImagesDataRepository @Inject constructor(private val retrofitServiceInterface: RetrofitServiceInterface?) {

    private var _imagesData: MutableLiveData<PixaBayImagesData>? =MutableLiveData()
    var imagesData:MutableLiveData<PixaBayImagesData>?=_imagesData
    //get data from API
    fun getPixaBayImages( requestState: MutableLiveData<RequestState>) : LiveData<PixaBayImagesData?>?{
        _imagesData?.value?.let {
            if(it.hits.isNotEmpty())
            {
                Log.e("PixaBayRepo", "isNotEmpty: ")
                requestState.value=RequestState.SUCCESS
                return _imagesData
            }
        }
        Log.e("PixaBayRepo", "RequestData: ")
        val call: Call<PixaBayImagesData>? =
            retrofitServiceInterface?.getFrameHeadersData(query = "kitten", prettyState = true)
        call?.let {
            Log.e("PixaBayRepo", "enqueue: ")
        }
        call?.enqueue(object : Callback<PixaBayImagesData> {
            override fun onResponse(
                call: Call<PixaBayImagesData>,
                response: Response<PixaBayImagesData>
            ) {
                if (response.isSuccessful) {
                    Log.e("PixaBayRepo", "onSuccess: ")
                    requestState.value=RequestState.SUCCESS
                    _imagesData?.value?.let {
                        if(it.hits.isEmpty()) _imagesData?.value = response.body()
                    }?: kotlin.run {
                        _imagesData?.value = response.body()
                    }
                }else{
                    Log.e("PixaBayRepo", "NotSuccess: ")
                }
            }

            override fun onFailure(call: Call<PixaBayImagesData>, t: Throwable) {
                Log.e("PixaBayRepo", "onFailure: " + t.message.toString())
                requestState.value=RequestState.FAILED
            }
        })
        return _imagesData
    }
}