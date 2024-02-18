package com.example.kotlin_crudappexample.crudApp2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_crudappexample.databinding.ActivityCrudApp2MainBinding
import kotlinx.coroutines.launch

class CrudApp2MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrudApp2MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrudApp2MainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            // Кнопка перехода на активити для добавления записи
            btnAdd.setOnClickListener {
                startActivity(Intent(this@CrudApp2MainActivity, AddActivity::class.java))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch{

            val userListInit = AppDatabase(this@CrudApp2MainActivity).getUserDao().getAllUser()
            val mAdapter = UserAdapter() // 1.Инициализация инстанции адаптера
            binding.recyclerView.layoutManager = LinearLayoutManager(this@CrudApp2MainActivity)
            binding.recyclerView.adapter = mAdapter // 2. Устанавливаем инстанцию адаптера в адаптер RecyclerView
            mAdapter.setData(userListInit) // 3. Через метод адаптера ".setData()" устанавливаем список пользователей в адаптер

            binding.recyclerView.apply {

                // Коллбэк (не кнопка!!!) изменения записи[вызывается из адаптера]
                mAdapter.setOnActionEditListener {
                    val intent = Intent(this@CrudApp2MainActivity, AddActivity::class.java)
                    intent.putExtra("Data", it)
                    startActivity(intent)
                }

                // Коллбэк (не кнопка!!!) удаления данных[вызывается из адаптера]
                mAdapter.setOnActionDeleteListener { user ->
                    val builder = AlertDialog.Builder(this@CrudApp2MainActivity)
                    builder.setMessage("Are you sure you want to delete")
                    builder.setPositiveButton("Yew") { dialog, which ->
                        lifecycleScope.launch{
                            AppDatabase(this@CrudApp2MainActivity).getUserDao().deleteUser(user)
                            val userListUpdate = AppDatabase(this@CrudApp2MainActivity).getUserDao().getAllUser()
                            mAdapter.setData(userListUpdate)
                        }
                        dialog.dismiss()
                    }
                    builder.setNegativeButton("No") { dialog, which ->
                        dialog.dismiss()
                    }
                    val dialog = builder.create()
                    dialog.show()
                }

                // Коллбэк (не кнопка!!!) по элементу
                mAdapter.clickItem { user ->
                    val checkVal = !user.isChecked
                    lifecycleScope.launch {
                        AppDatabase(this@CrudApp2MainActivity).getUserDao().checkItem(user.id, checkVal)
                        val userListUpdate = AppDatabase(this@CrudApp2MainActivity).getUserDao().getAllUser()
                        mAdapter.setData(userListUpdate)
                    }
                }
            }
        }
    }
}