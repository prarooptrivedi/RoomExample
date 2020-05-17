package com.example.roomsample.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDao {
    @Insert
    suspend fun insertsbscriber(subscriber: Subscriber):Long



    @Update
    suspend fun updatesbscriber(subscriber: Subscriber):Int

    @Delete
    suspend fun deletesbscriber(subscriber: Subscriber):Int

    @Query(value = "DELETE FROM subscriber_data_table")
    suspend fun deleteAll():Int



    @Query(value = "SELECT * FROM subscriber_data_table")
    fun getAllSubscribers():LiveData<List<Subscriber>>
}