package com.example.kotlinpr3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.kotlinpr3.R
import com.example.kotlinpr3.databinding.ActivityMealBinding
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.ui.HomeFragment
import com.example.kotlinpr3.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMealBinding
    private lateinit var meadId:String
    private lateinit var meadName:String
    private lateinit var meadThumb:String
    private lateinit var  mealMvvm:MealViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]
        getMealInfo()
        setInfo()
        mealMvvm.getMealDetail(meadId)
        observerMealDetailsLiveData()
    }

    private fun observerMealDetailsLiveData() {
        mealMvvm.observeLiveData().observe(this,object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                val meal = value
                binding.tvInstructions.text = meal.strInstructions
            }

        })
    }

    private fun setInfo() {
        Glide.with(applicationContext)
            .load(meadThumb)
            .into(binding.imgMealDetail)
        binding.collapsingToolbar.title = meadName
       // binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun getMealInfo() {
        val intent = intent
        meadId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        meadName = intent.getStringExtra(HomeFragment.MEAL_Name)!!
        meadThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }
}