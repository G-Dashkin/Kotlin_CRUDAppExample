package com.example.kotlin_crudappexample.crudApp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.kotlin_crudappexample.databinding.ActivityAddBinding
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null){
            user = intent.getSerializableExtra("Data") as User
        }

        if (user == null) binding.btnAddOrUpdateUser.text = "Add User"
        else {
            binding.btnAddOrUpdateUser.text = "Update"
            binding.edFirsName.setText(user?.firstName.toString())
            binding.edLastName.setText(user?.lastName.toString())
        }

        binding.btnAddOrUpdateUser.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val firsName = binding.edFirsName.text.toString()
        val lastName = binding.edLastName.text.toString()

        lifecycleScope.launch{
            if (user == null) {
                val user = User(firstName = firsName, lastName = lastName)
                AppDatabase(this@AddActivity).getUserDao().addUser(user)
                finish()
            } else {
                val u = User(firsName, lastName)
                u.id = user?.id ?: 0
                AppDatabase(this@AddActivity).getUserDao().updateUser(u)
                finish()
            }
        }
    }
}