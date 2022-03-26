package com.kingfishapps.viewmodel

import com.kingfishapps.time.TimeSeries
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url

interface TimeSeriesInterface {
    @GET

    fun TimeSeriesInfom(@Url url:String):retrofit2.Call<TimeSeries>


}
const val base_url="https://fxmarketapi.com/"

class TimeSeriesInstance(retrofit: Retrofit)
{
    val timeSeriesInterface:TimeSeriesInterface
    init {
       // println("App staus : $msg")
       /* val retrofit= Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()*/

        timeSeriesInterface=retrofit.create(TimeSeriesInterface::class.java)


    }
}