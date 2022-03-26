package com.kingfishapps.models.currencies

data class CurrencyExRate(
    val end_date: String,
   val price:HashMap<String,Double>,
   val start_date: String)

