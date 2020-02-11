package com.example.trainning.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.trainning.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_bottom.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainHomeFragment : Fragment() {

    private val mNavigation = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.nav_today -> {
                createToday()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_one -> {
                createOne()
                return@OnNavigationItemSelectedListener true
            } R.id.nav_two -> {
            createTwo()
            return@OnNavigationItemSelectedListener true
        }R.id.nav_three -> {
            createThree()
            return@OnNavigationItemSelectedListener true
        }
        }
        true
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createToday()
        bottomNav.setOnNavigationItemSelectedListener(mNavigation)
    }
    fun createToday(){
        val transaction = childFragmentManager.beginTransaction()
        val fragment = TodayFragment()
        transaction.replace(R.id.line,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun createOne(){
        val transaction = childFragmentManager.beginTransaction()
        val fragment = OneFragment()
        transaction.replace(R.id.line,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun createTwo(){
        val transaction = childFragmentManager.beginTransaction()
        val fragment = TwoFragment()
        transaction.replace(R.id.line,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    fun createThree(){
        val transaction = childFragmentManager.beginTransaction()
        val fragment = ThreeFragment()
        transaction.replace(R.id.line,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}