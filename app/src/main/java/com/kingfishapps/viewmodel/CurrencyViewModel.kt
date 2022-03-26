package com.kingfishapps.viewmodel

import android.annotation.SuppressLint
import android.os.Build
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.kingfishapps.fx.R
import com.kingfishapps.models.currencies.CurrencyExRate
import com.kingfishapps.time.TimeSeries
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import java.time.LocalDate

class CurrencyViewModel: ViewModel() {
    private var currencyData:ArrayList<Double?>? = null;

    fun getCurrnecyData():ArrayList<Double?>?
    {
        return currencyData
    }


    var currency:String="EURUSD";
    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrency(linegraph:LineChart,background:Int,currency:String,retrofit: Retrofit)
    {

        var dateNow= LocalDate.now()
        var endDate=dateNow
        var startDate=dateNow.minusDays(60)//minusing 60 in order to only include weekdays
        //the app will crash if we request dates for weekends hence weekends have to be removed
        if((endDate.dayOfWeek.value == 6) || endDate.dayOfWeek.value == 7)
        {
            endDate=endDate.minusDays(2)
        }
        if((startDate.dayOfWeek.value == 6) || startDate.dayOfWeek.value == 7)
        {
            startDate=startDate.minusDays(2)
        }




        println("Start Date : ${startDate}")
        println("End Date : ${endDate}")
             val url="https://fxmarketapi.com/apitimeseries?api_key=q5GIw4eT-TpRjLQndKQ3&currency=${currency}&start_date=${startDate.toString()}&end_date=${endDate.toString()}"
        println("URL ${url}")

        val process=ProcessData()
        val timeSeries = TimeSeriesInstance(retrofit).timeSeriesInterface.TimeSeriesInfom(url);
        timeSeries.enqueue(object : retrofit2.Callback<TimeSeries> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<TimeSeries>,
                response: Response<TimeSeries>
            ) {

                val timeSeriesInfo: TimeSeries? = response.body();
                if (timeSeriesInfo != null) {
                    println("Start Date : ${timeSeriesInfo.start_date}")
                    var startDate=timeSeriesInfo.start_date;
                    /*var currency:String="EURUSD"*/
                    //println("Price : ${timeSeriesInfo.price.get("2018-07-03")?.get("EURUSD")?.get("high")}")
                   // println("TimeSeries;${}")
                    var currnecyHighs=process.addCurrencyValues(startDate,currency,timeSeriesInfo)
                    println("Day 30 Highest : ${currnecyHighs[29]}")
                    currencyData=currnecyHighs;
                    setLineChartData(currnecyHighs,linegraph,background)
                }

            }

            override fun onFailure(call: Call<TimeSeries>, t: Throwable) {
                //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show();

                println("Error msg: ${t.message}")
                currencyData= ArrayList<Double?>();
            }

        })

    }

    @SuppressLint("ResourceAsColor")
    private fun setLineChartData(currencyData:ArrayList<Double?>?,linechart: LineChart,bg:Int) {
        val linevalues = ArrayList<Entry>()
        if (currencyData != null) {
            for(i in 0..currencyData.size-1)
            {
                println("Looping")

                println("Currency Data ${currencyData[i]}")
                if(currencyData[i] != null)
                {
                    val y:Float= currencyData[i]?.toFloat()!!;

                    val x:Float=i.toFloat();
                    linevalues.add(Entry(x, y))



                }

            }
        }



       /* linevalues.add(Entry(10F, 0.0F))
        linevalues.add(Entry(30f, 0.2F))
        linevalues.add(Entry(40f, 0.8F))
        linevalues.add(Entry(50f, 1.0F))
        linevalues.add(Entry(0.3F, 1.0F))

        linevalues.add(Entry(80f, 1.0F))
        linevalues.add(Entry(90f, 0.7F))
        linevalues.add(Entry(100f, 1.0F))
        linevalues.add(Entry(110f, 1.0F))*/


        val linedataset = LineDataSet(linevalues, "First")
        //We add features to our chart
        linedataset.color = R.color.purple_200
        linedataset.disableDashedLine()
        //linedataset.circleRadius = 10f
        linedataset.setDrawFilled(true)
        linedataset.valueTextSize = 0F
        linedataset.fillColor = R.color.green
        linedataset.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        //We connect our data to the UI Screen
        val data = LineData(linedataset)
        linechart.data = data


        linechart.setBackgroundColor(bg)
        linechart.animateXY(2000, 2000, Easing.EaseInCubic)



    }
    fun getCurrentExRate(currencyName:String,tv:TextView,retrofit: Retrofit)
    {
        var endpoint:String="https://fxmarketapi.com/apilive?api_key=q5GIw4eT-TpRjLQndKQ3&currency=${currencyName}"
        val currencyExRate = CurrencyExRateInstance(retrofit).currencyExRateInterface.CurrencyExRateInfom(endpoint);
        currencyExRate.enqueue(object : retrofit2.Callback<CurrencyExRate> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(
                call: Call<CurrencyExRate>,
                response: Response<CurrencyExRate>
            ) {

                val currencyExInfo: CurrencyExRate? = response.body();
                if (currencyExInfo != null) {
                    val currentExRt=currencyExInfo.price.get(currencyName)
                    tv.setText("${currentExRt}  ${currencyName}")


                }

            }

            override fun onFailure(call: Call<CurrencyExRate>, t: Throwable) {
                //Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show();

                println("Error msg: ${t.message}")
                currencyData= ArrayList<Double?>();
            }

        })

    }

}