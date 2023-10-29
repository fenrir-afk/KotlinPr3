package com.example.kotlinpr3.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealList
import com.example.kotlinpr3.retrofit.MealApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class HomeViewModel @Inject constructor() :ViewModel() {
    @Inject
    lateinit var randomMealLivedata:MutableLiveData<Meal>
    @Inject
    lateinit var retrofit:MealApi
    fun getRandomMeal(){
        retrofit.getRandomMeal().enqueue(object : Callback<MealList> {
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