package com.kingfishapps.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import com.kingfishapps.time.TimeSeries
import java.time.LocalDate

class ProcessData {
    private var currencyValues:ArrayList<Double?> =ArrayList<Double?>();
    private var dates:ArrayList<String> =ArrayList<String>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addCurrencyValues(date:String, currency:String, timeSeriesInfo: TimeSeries?):ArrayList<Double?>
    {


        var i:Long=0




        while(i < 30)
        {

            var startDate = LocalDate.parse(date)
            //datee.plusDays(2);
            //add to dates array
            var currentDate=startDate.plusDays(i).toString()
            dates.add(currentDate)
            if (timeSeriesInfo != null) {
                var currencyHigh=timeSeriesInfo.price.get(currentDate)?.get(currency)?.get("high")
                currencyValues.add(currencyHigh)
            }

            //end date info
                //every day add data
            i++
        }

        return currencyValues
    }
    fun getDate():ArrayList<String>
    {
        return dates
    }
    fun getCurrencyHigh():ArrayList<Double?>
    {
        return currencyValues
    }


}