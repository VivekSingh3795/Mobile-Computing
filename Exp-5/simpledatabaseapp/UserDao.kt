package com.example.simpledatabaseapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table ORDER BY id DESC")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
    @androidx.room.Delete
    suspend fun deleteUser(user: User)
}