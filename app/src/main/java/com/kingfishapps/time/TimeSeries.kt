package com.kingfishapps.time


data class TimeSeries(
    val end_date: String,
    //val price: HashMap<String,HashMap<String,HashMap<String,Currency>>> ,
    val price:HashMap<String,HashMap<String,HashMap<String,Double>>>,
    val start_date: String
)
data class Currency(
    val close:Double,
    val high:Double,
    val low:Double,
    val open:Double,
)
