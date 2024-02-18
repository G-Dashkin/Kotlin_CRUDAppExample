package com.example.kotlin_crudappexample.crudApp2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_crudappexample.R
import com.example.kotlin_crudappexample.databinding.CardItemUserViewHolderBinding

class UserAdapter: RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var list = mutableListOf<User>()
    private var actionEdit: ((User) -> Unit)? = null
    private var actionDelete: ((User) -> Unit)? = null
    private var clickItem: ((User) -> Unit)? = null // 1.Создаем экземпляр коллбэка в который мы передаем объект класса User

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewBinding = CardItemUserViewHolderBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(viewBinding)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.tvFirstName.text = user.firstName
        holder.tvLastName.text = user.lastName

        holder.actionEdit.setOnClickListener { actionEdit?.invoke(user) }
        holder.actionDelete.setOnClickListener { actionDelete?.invoke(user) }
        holder.itemElement.setOnClickListener { clickItem?.invoke(user) } //2.При клике по элементу вызываем этот коллбэк при помощи метода .invoke() и передаем туда объект user по которому был клик
        if (user.isChecked){
            holder.itemElement.setBackgroundColor(R.color.mainColor)
        } else {
            holder.itemElement.setBackgroundColor(R.color.hintColor)
        }
    }

    override fun getItemCount() = list.size

    fun setData(data: List<User>) {
        list.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun setOnActionEditListener(callback: (User) -> Unit){
        this.actionEdit = callback
    }

    fun setOnActionDeleteListener(callback: (User) -> Unit){
        this.actionDelete = callback
    }

    // 3.Cоздаем функцию, которую мы будем вызывать из адаптера при коллбэке в MainActivity
    // т.е. при клике во вью холдере у нас будет вызваться коллбэк
    fun clickItem(callback: (User) -> Unit){
        this.clickItem = callback   // при вызове этой функции в коллбэк из вьюХолдера будет помещаться коллбэк переданные в MainActivity
                                    // т.е. MainActivity вы вызываем эту функцию, передаем туда объект User...
    }

    class UserViewHolder(val binding: CardItemUserViewHolderBinding): RecyclerView.ViewHolder(binding.root){
        val tvFirstName = itemView.findViewById<TextView>(R.id.tv_first_name)
        val tvLastName = itemView.findViewById<TextView>(R.id.tv_last_name)
        val actionEdit = itemView.findViewById<ImageView>(R.id.action_edit)
        val actionDelete = itemView.findViewById<ImageView>(R.id.action_delete)
        val itemElement = itemView.findViewById<LinearLayout>(R.id.listItemElement)
    }
}