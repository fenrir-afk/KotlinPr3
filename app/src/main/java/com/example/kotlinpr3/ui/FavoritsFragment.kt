package com.example.kotlinpr3.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpr3.R
import com.example.kotlinpr3.adapter.MealAdapter
import com.example.kotlinpr3.databinding.FragmentFavoritsBinding
import com.example.kotlinpr3.model.Meal
import com.example.kotlinpr3.model.MealSimple
import com.example.kotlinpr3.viewModel.FavoritsViewModel
import com.example.kotlinpr3.viewModel.HomeViewModel

class FavoritsFragment : Fragment() {
    lateinit var binding: FragmentFavoritsBinding
    lateinit var adapter: MealAdapter
    lateinit var recyclerView: RecyclerView

    private  var favoMvvm: FavoritsViewModel = FavoritsViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritsBinding.inflate(layoutInflater)
        initial(binding.root.context)

        favoMvvm = FavoritsViewModel()
        binding.addButton.setOnClickListener{
            add(it.context)
        }

        return binding.root
    }
    fun add(context: Context){
        //Создаем адаптер для метода observeMealList
        recyclerView =binding.recyclerView
        adapter = MealAdapter()
        //
        val title = binding.editText1Id.text.toString()
        val description = binding.editText2Id.text.toString()
        favoMvvm.insertDataInDatabase(description,title,context)
        binding.editText1Id.text.clear()
        binding.editText2Id.text.clear()
        //Редактируем адаптер и добавляем его в reccylerview
        observeMealList(context,adapter)
        recyclerView.adapter = adapter
        //
    }
    private fun initial(context: Context) {
        recyclerView =binding.recyclerView
        adapter = MealAdapter()
        observeMealList(context,adapter)
        recyclerView.adapter = adapter
    }
    fun observeMealList(context: Context,adapter: MealAdapter){
        favoMvvm.getAllMeals(context).observe(viewLifecycleOwner, object  : Observer<List<MealSimple>>{
            override fun onChanged(value: List<MealSimple>) {
                adapter.setList(value)
            }

        })
    }

}