package com.example.androidtask.network.appmodule

import android.content.Context
import com.example.androidtask.network.retrofitInterfaces.RetrofitServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val BASE_URL = "https://pixabay.com/"

    @Provides
    @Singleton
    fun getContext(@ApplicationContext context: Context): Context {
        return context
    }




    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit?): RetrofitServiceInterface? {
        return retrofit?.create(RetrofitServiceInterface::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit? {
        var httpClientBuilderJob: OkHttpClient.Builder? = null
        httpClientBuilderJob = OkHttpClient.Builder()
        httpClientBuilderJob?.addInterceptor { chain ->
            val request: Request = chain.request().newBuilder()
                .header("Accept", "application/json")
                .build()
            chain.proceed(request)
        }
        val httpClient = httpClientBuilderJob?.build()
        httpClient?.let {

         return   Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return null
    }


}
