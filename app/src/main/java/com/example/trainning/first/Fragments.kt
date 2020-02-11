package com.example.trainning.first


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class Fragments (fm: FragmentManager): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 ->{
                OneFragment()
            }
            1 -> {
                TwoFragment()
            }
            2 -> ThreeFragment()
            else -> {
                return FourFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 4
    }

}