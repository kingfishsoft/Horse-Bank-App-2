package com.kingfishapps.viewmodel


import com.kingfishapps.models.currencies.CurrencyExRate
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url

interface CurrencyExRateInterface {
    @GET

    fun CurrencyExRateInfom(@Url url:String):retrofit2.Call<CurrencyExRate>


}


class CurrencyExRateInstance(retrofit: Retrofit)
{
    val currencyExRateInterface:CurrencyExRateInterface
    init {
        // println("App staus : $msg")


        currencyExRateInterface=retrofit.create(CurrencyExRateInterface::class.java)


    }
}