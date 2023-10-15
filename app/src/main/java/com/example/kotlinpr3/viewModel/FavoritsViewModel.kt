package com.example.kotlinpr3.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealSimple
import com.example.kotlinpr3.retrofit.RetrofitInstance
import com.example.kotlinpr3.room.MealDatabase
import com.example.kotlinpr3.room.MealRepository
import kotlinx.coroutines.launch

class FavoritsViewModel:ViewModel() {
    private var randomMealListLivedata = MutableLiveData<List<MealSimple>>()
    fun insertDataInDatabase(description:String,name:String,context: Context) {
        viewModelScope.launch {
            val newMeal = MealSimple(description = description, name = name)
            val mealRepository: MealRepository = MealRepository(MealDatabase.getInstance(context).mealDao())
            mealRepository.insertNewMeal(newMeal)
        }
    }
    fun getAllMeals(context: Context) :MutableLiveData<List<MealSimple>>{
        viewModelScope.launch {
            val mealRepository: MealRepository = MealRepository(MealDatabase.getInstance(context).mealDao())
            randomMealListLivedata.value = mealRepository.getAll()
        }
        return randomMealListLivedata
    }

}