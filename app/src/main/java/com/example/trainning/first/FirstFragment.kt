package com.example.trainning.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainning.R
import com.example.trainning.data.PreferenceUtils
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_first_screen.*

class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentAdapter = Fragments(childFragmentManager)
        viewPager.adapter = fragmentAdapter
        circleIndicator.setViewPager(viewPager)
        fragmentAdapter.registerDataSetObserver(circleIndicator.dataSetObserver)
        check()
    }

    private fun check() {
        btnRegister.setOnClickListener {
            if (PreferenceUtils(context, Gson()).getListChild() == null)
                findNavController().navigate(R.id.action_firstFragment_to_registerUserFragment)
            else
                findNavController().navigate(R.id.action_registerUserFragment_to_registerChildFragment)
        }
        tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_login)
        }
    }

}
