package com.example.trainning.register.child

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainning.R
import com.example.trainning.models.Child
import kotlinx.android.synthetic.main.list_register_child.view.*

class RegisterChildAdapter(context: Context, private val listener: ListenerChildClicked) : RecyclerView.Adapter<RegisterChildAdapter.ChildHolder>(){
    private var listChild: MutableList<Child>? = null
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun setData(listChild: MutableList<Child>?){
        this.listChild = listChild
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildHolder {
        return ChildHolder(inflater.inflate(R.layout.list_register_child, parent, false))
    }

    override fun onBindViewHolder(holder: ChildHolder, position: Int) {
        listChild?.let {
            holder.bindView(it[position])
            holder.itemView.setOnClickListener { listener.onListenerChildClicked(position) }
        }
    }
    override fun getItemCount() = listChild?.size?: 0

    @Suppress("DEPRECATION")
    inner class ChildHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(child: Child){
            itemView.tvChild.text = child.name?: "Child $position"
            itemView.tvStt.text = (position + 1).toString()
        }
    }

    interface ListenerChildClicked{
        fun onListenerChildClicked(position: Int)
    }
}