package com.example.kotlin_crudappexample.crudApp3

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert
    suspend fun insertSubscriber(subscriber: Subscriber): Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM subscribers_data_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM subscribers_data_table")
    fun getAllSubscribers(): LiveData<List<Subscriber>>

}