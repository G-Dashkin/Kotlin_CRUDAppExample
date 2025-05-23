package com.example.kotlin_crudappexample.crudApp3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_crudappexample.R
import com.example.kotlin_crudappexample.databinding.ListItemBinding

class MyRecyclerViewAdapter(private val subscriberList: List<Subscriber>, private val clickListener:(Subscriber) -> Unit): RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater : LayoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return subscriberList.size
    }
}

class MyViewHolder(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriber: Subscriber, clickListener:(Subscriber) -> Unit) {
        binding.nameTextView.text = subscriber.name
        binding.emailTextView.text = subscriber.email
        binding.listItemLayout.setOnClickListener {
            clickListener(subscriber)
        }
    }
}