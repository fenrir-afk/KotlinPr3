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

class MealViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var  mealDetailListLiveData:MutableLiveData<Meal>
    @Inject
    lateinit var retrofit: MealApi
    fun getMealDetail(id:String){
        retrofit.getMealDetails(id).enqueue(object : Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null){
                    mealDetailListLiveData.value = response.body()!!.meals[0]
                }else{
                    return
                }
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("MealActivity",t.message.toString())
            }

        })
    }
    fun observeLiveData():LiveData<Meal>{
        return mealDetailListLiveData
    }
}