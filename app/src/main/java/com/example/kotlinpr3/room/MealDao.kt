package com.example.kotlinpr3.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealSimple

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal: MealSimple?)
    @Delete
    fun delete(meal: MealSimple?)
    @Query("SELECT * FROM table_name")
    fun getAll(): List<MealSimple>?
}