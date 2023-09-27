package com.example.androidtask.network.retrofitInterfaces

import com.example.androidtask.datamodels.PixaBayImagesData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitServiceInterface {
    //Frame Headers
    @GET("api")
    fun getFrameHeadersData(@Query("key") key:String="38437071-5f1d14114d464cb7440d92ebd",@Query("q") query:String,
                             @Query("image_type") imageType:String="photo",
                            @Query("pretty") prettyState:Boolean=false ): Call<PixaBayImagesData>


}