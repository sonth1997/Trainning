package com.example.trainning.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.trainning.R
import kotlinx.android.synthetic.main.fragment_bottom.*

class TodayFragment : Fragment() {
    lateinit var adapter: MyFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = MyFragment(childFragmentManager)
        adapter.addFragment(TopFragment(),"TOP")
        adapter.addFragment(MRFragment(),"MR")
        adapter.addFragment(ADHDFragment(),"ADHD")
        adapter.addFragment(LDFragment(),"LD")
        viewPagerMain.adapter = adapter
        tabLayout.setupWithViewPager(viewPagerMain)
    }
    inner class MyFragment(manager: FragmentManager): FragmentPagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
        val fragmentList: MutableList<Fragment> = ArrayList()
        val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }
        override fun getCount(): Int {
            return fragmentList.size
        }
        fun addFragment(fragment: Fragment,title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }
        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }
    }
}