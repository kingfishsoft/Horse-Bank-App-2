package com.kingfishapps.models.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CurrencyHighsDao {
    @Query("SELECT * FROM CurrencyHighs")
    fun getAll(): List<CurrencyHighs>

    @Query("SELECT * FROM CurrencyHighs WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<CurrencyHighs>

    @Query("SELECT * FROM CurrencyHighs WHERE currency_name LIKE :currency_name AND " +
            "day_high LIKE :last LIMIT 1")
    fun findByName(currency_name: String, day_high: String): CurrencyHighs

    @Insert
    fun insertAll(vararg users: CurrencyHighs)

    @Delete
    fun delete(user: CurrencyHighs)
}