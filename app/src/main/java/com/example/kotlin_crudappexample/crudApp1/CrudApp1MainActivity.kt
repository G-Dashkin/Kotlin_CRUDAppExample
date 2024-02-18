package com.example.kotlin_crudappexample.crudApp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_crudappexample.databinding.ActivityCrudApp1MainBinding

class CrudApp1MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCrudApp1MainBinding
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudApp1MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

            todoAdapter = TodoAdapter(mutableListOf())
            rvTodoItems.adapter = todoAdapter
            rvTodoItems.layoutManager = LinearLayoutManager(this@CrudApp1MainActivity)

            btnAddTodo.setOnClickListener {
                val todoTitle = etTodoTitle.text.toString()
                if (todoTitle.isNotEmpty()) {
                    val todo = Todo(todoTitle)
                    todoAdapter.addTodo(todo)
                    etTodoTitle.text.clear()
                }
            }
            btnDeleteDoneTodos.setOnClickListener {
                todoAdapter.deleteDoneTodos()
            }


        }
    }
}