package com.example.trainning.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trainning.R
import kotlinx.android.synthetic.main.item_main.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainAdapter (val mlistener:onClickItemMain): RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    private var mData: MutableList<Main> = mutableListOf()
    fun setData(mData:MutableList<Main>){
        this.mData = mData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(mData[position])
        holder.itemView.setOnClickListener {
            mlistener.onClickItemMain(mData[position])
        }
    }

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        fun onBind(main: Main){
            itemView.btnItemMain.text = main.button
            itemView.tvItem1.text = main.text1
            itemView.tvItem2.text = main.text2
            itemView.tvItem3.text = main.text3

        }
    }
    interface onClickItemMain{
        fun onClickItemMain(main: Main)
    }

}