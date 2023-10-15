package com.example.kotlinpr3.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealList
import com.example.kotlinpr3.model.MealSimple
import com.example.kotlinpr3.retrofit.RetrofitInstance
import com.example.kotlinpr3.room.MealDatabase
import com.example.kotlinpr3.room.MealRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response






class HomeViewModel:ViewModel() {
    private var randomMealLivedata = MutableLiveData<Meal>()
    fun getRandomMeal(){
        RetrofitInstance.foodApi.getRandomMeal().enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null){//!! - NotNUll
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLivedata.value = randomMeal
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment",t.message.toString())
            }
        })

    }
    fun observeRandomMealLivedata(): LiveData<Meal>{
        return randomMealLivedata
    }


}