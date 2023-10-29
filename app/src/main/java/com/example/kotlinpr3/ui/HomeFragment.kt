package com.example.kotlinpr3.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.kotlinpr3.Application.MyApplication
import com.example.kotlinpr3.activities.MealActivity
import com.example.kotlinpr3.databinding.FragmentHomeBinding
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.viewModel.HomeViewModel
import javax.inject.Inject


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding

    @Inject
    lateinit var homeMvvm: HomeViewModel
    lateinit var randomMeal:Meal

    companion object{
        val MEAL_ID = "com.example.kotlinpr3.ui.idMeal"
        val MEAL_Name = "com.example.kotlinpr3.ui.nameMeal"
        val MEAL_THUMB = "com.example.kotlinpr3.ui.thumbMeal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observeRandomMeal()
        onRandomMealClick()

    }

    private fun onRandomMealClick() {
        binding.imgRandomMeal.setOnClickListener{
            val intent = Intent(activity,MealActivity::class.java)
            intent.putExtra(MEAL_ID,randomMeal.idMeal)
            intent.putExtra(MEAL_Name,randomMeal.strMeal)
            intent.putExtra(MEAL_THUMB,randomMeal.strMealThumb)
            startActivity(intent)
        }
    }

    private fun observeRandomMeal() {
        homeMvvm.observeRandomMealLivedata().observe(viewLifecycleOwner, object  : Observer<Meal>{
            override fun onChanged(value: Meal) {
                Glide.with(this@HomeFragment)
                    .load(value.strMealThumb)
                    .into(binding!!.imgRandomMeal)
                this@HomeFragment.randomMeal = value
            }

        })
    }

}