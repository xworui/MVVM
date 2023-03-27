package com.example.approomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.approomdb.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var bind: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        setupActionBarWithNavController(findNavController(R.id.fragmentView))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController((R.id.fragmentView))

        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}