package com.example.kotlin_crudappexample.crudApp2

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user ORDER BY id DESC")
    suspend fun getAllUser(): List<User>

    @Query("UPDATE user SET isChecked = :check WHERE id = :id")
    suspend fun checkItem(id: Int, check: Boolean)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}