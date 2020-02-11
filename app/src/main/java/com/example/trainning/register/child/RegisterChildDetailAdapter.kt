package com.example.trainning.register.child

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.trainning.R

class RegisterChildDetailAdapter(var context: Context, var expandableListView: ExpandableListView, var header: MutableList<String>, var body: MutableList<MutableList<Int>>) : BaseExpandableListAdapter(){
    override fun getGroup(groupPosition: Int): String {
        return header[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.list_register_child,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tvChild)
        title?.text = getGroup(groupPosition).toString()
        title?.setOnClickListener {
            if(expandableListView.isGroupExpanded(groupPosition))
                expandableListView.collapseGroup(groupPosition)
            else
                expandableListView.expandGroup(groupPosition)
        }
        return convertView
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return body[groupPosition].size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Int {
        return body[groupPosition][childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    @SuppressLint("InflateParams")
    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if(convertView == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = inflater.inflate(R.layout.item_register_child,null)
        }
        val title = convertView?.findViewById<TextView>(R.id.tvChild)
        title?.text = getChild(groupPosition,childPosition).toString()
        title?.setOnClickListener {
            Toast.makeText(context, getChild(groupPosition,childPosition),Toast.LENGTH_SHORT).show()
        }
//        val imageView = convertView?.findViewById<ImageView>(R.id.imgDown)
//        imageView?.setOnClickListener {
//                if (imageView.is)
//        }
        return convertView
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return header.size
    }

}
