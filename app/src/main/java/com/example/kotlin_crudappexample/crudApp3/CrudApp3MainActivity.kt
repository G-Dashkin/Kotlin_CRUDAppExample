package com.example.kotlin_crudappexample.crudApp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_crudappexample.R
import com.example.kotlin_crudappexample.databinding.ActivityCrudApp3MainBinding

class CrudApp3MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityCrudApp3MainBinding
    private lateinit var dataBinding: ActivityCrudApp3MainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityCrudApp3MainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_crud_app3_main)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_crud_app3_main)
        val dao : SubscriberDAO = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this, factory).get(SubscriberViewModel::class.java)
        dataBinding.myViewModel = subscriberViewModel
        dataBinding.lifecycleOwner = this
        iniRecyclerView()

//        binding.apply {
//
//
//        }
    }
    private fun iniRecyclerView() {
        dataBinding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displaysSubscribersList()
    }

    private fun displaysSubscribersList() {
        subscriberViewModel.subscribers.observe(this, Observer {
            Log.d("MyLog", it.toString())
            dataBinding.subscriberRecyclerView.adapter = MyRecyclerViewAdapter(it) { selectedItem: Subscriber ->
                listItemClicked(selectedItem)
            }
        })
    }

    private fun listItemClicked(subscriber: Subscriber) {
        Toast.makeText(this, "selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }

}