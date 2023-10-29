package com.example.kotlinpr3.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinpr3.model.MealSimple

@Database(entities = [MealSimple::class], version = 1)
abstract class MealDatabase(): RoomDatabase() {
    abstract fun mealDao(): MealDao


    companion object{
       @Volatile
       var INSTANCE: MealDatabase? = null
       @Synchronized
       fun getInstance(context: Context):MealDatabase{
           if (INSTANCE == null){
               INSTANCE = Room.databaseBuilder(
                   context,
                   MealDatabase::class.java,
                   "MealDB"
               ).fallbackToDestructiveMigration().build()
           }
           return  INSTANCE as MealDatabase
       }
       
   }
}