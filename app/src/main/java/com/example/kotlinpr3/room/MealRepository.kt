package com.example.kotlinpr3.room

import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealSimple
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MealRepository(private val mealDao: MealDao) {
    suspend fun insertNewMeal(meal: MealSimple) {
        withContext(Dispatchers.IO) {
            mealDao.insert(meal)
        }
    }

    suspend fun getAll(): List<MealSimple> {
        return withContext(Dispatchers.IO) {
            return@withContext mealDao.getAll()!!
        }
    }
    suspend fun delete(meal: MealSimple){
        withContext(Dispatchers.IO){
            mealDao.delete(meal)
        }
    }
}