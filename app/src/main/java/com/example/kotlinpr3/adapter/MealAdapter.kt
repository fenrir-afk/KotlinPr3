package com.example.kotlinpr3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpr3.R
import com.example.kotlinpr3.model.MealSimple


class MealAdapter: RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name:TextView = itemView.findViewById(R.id.name)
        var description:TextView = itemView.findViewById(R.id.description)
    }
    private var mealList = emptyList<MealSimple>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        //mea_item_layout - это наш cardview
        var view = LayoutInflater.from(parent.context).inflate(R.layout.meal_item_layout,parent,false)
    return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.name.text= mealList[position].name
        holder.description.text = mealList[position].description
    }
    fun setList(List:List<MealSimple>){
        mealList = List
        //Извещает адаптер об изменениях( без того никак)
        notifyDataSetChanged()
    }
}