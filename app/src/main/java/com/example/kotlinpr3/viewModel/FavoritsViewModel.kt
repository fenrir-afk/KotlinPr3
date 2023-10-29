package com.example.kotlinpr3.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpr3.model.MealSimple
import com.example.kotlinpr3.room.MealRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoritsViewModel @Inject constructor():ViewModel() {
    @Inject
    lateinit var randomMealListLivedata:MutableLiveData<List<MealSimple>>
    @Inject
    lateinit var mealRepository: MealRepository
    fun insertDataInDatabase(description:String,name:String,context: Context) {
        viewModelScope.launch {
            val newMeal = MealSimple(description = description, name = name)
            mealRepository.insertNewMeal(newMeal)
        }
    }
    fun getAllMeals(context: Context) :MutableLiveData<List<MealSimple>>{
        viewModelScope.launch {
            randomMealListLivedata.value = mealRepository.getAll()
        }
        return randomMealListLivedata
    }

}