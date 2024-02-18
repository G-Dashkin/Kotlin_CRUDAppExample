package com.example.kotlin_crudappexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_crudappexample.crudApp1.CrudApp1MainActivity
import com.example.kotlin_crudappexample.crudApp2.CrudApp2MainActivity
import com.example.kotlin_crudappexample.crudApp3.CrudApp3MainActivity
import com.example.kotlin_crudappexample.crudApp4.CrudApp4MainActivity
import com.example.kotlin_crudappexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            btnCrudApp1.setOnClickListener {
                startActivity(Intent(this@MainActivity, CrudApp1MainActivity::class.java))
            }

            btnCrudApp2.setOnClickListener {
                startActivity(Intent(this@MainActivity, CrudApp2MainActivity::class.java))
            }

            btnCrudApp3.setOnClickListener {
                startActivity(Intent(this@MainActivity, CrudApp3MainActivity::class.java))
            }

            btnCrudApp4.setOnClickListener {
                startActivity(Intent(this@MainActivity, CrudApp4MainActivity::class.java))
            }

        }
    }
}