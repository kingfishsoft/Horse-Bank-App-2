package com.kingfishapps.models.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyHighs(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "currency_name") val currencyName: String?,
    @ColumnInfo(name = "day_high") val dayHigh: Double?
    ) {
}