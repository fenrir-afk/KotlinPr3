package com.example.kotlinpr3.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_name")
data class MealSimple(
    @PrimaryKey(autoGenerate = true)
    val idMeal: Int = 0,
    val description: String,
    val name: String
)