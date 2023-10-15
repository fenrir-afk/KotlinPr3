package com.example.kotlinpr3.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.kotlinpr3.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottonNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController =Navigation.findNavController(this, R.id.frag_host)
        NavigationUI.setupWithNavController(bottonNavigation,navController)
        print("Its my test_branch")
    }
}