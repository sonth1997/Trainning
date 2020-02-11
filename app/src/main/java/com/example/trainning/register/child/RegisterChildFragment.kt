package com.example.trainning.register.child

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainning.R
import com.example.trainning.data.PreferenceUtils
import com.example.trainning.models.Child
import com.example.trainning.models.GENDER
import com.example.trainning.models.SICK
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_register_child.*
import java.util.*

class RegisterChildFragment : Fragment(), RegisterChildAdapter.ListenerChildClicked {


    private var mAdapterChild: RegisterChildAdapter? = null
    private var mListChild: MutableList<Child> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_child, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        initRecycle()
        onClick()

    }
    override fun onResume() {
        super.onResume()
        initData()
    }
    private fun initData() {
        PreferenceUtils(context, Gson()).getListChild()?.let {
            mListChild = it
            this.mAdapterChild?.setData(mListChild)
        }
    }
    private fun initRecycle() {
        context?.let {
            mAdapterChild = RegisterChildAdapter(it, this)
            lvListChild.adapter = mAdapterChild
        }
    }
    private fun onClick(){
        btnFinish.setOnClickListener {
            findNavController().navigate(R.id.action_registerChildFragment_to_main)
        }
        btnClearCache.setOnClickListener {
            PreferenceUtils(context, Gson()).clearCache()
            findNavController().popBackStack()
        }
    }

    override fun onListenerChildClicked(position: Int) {
        findNavController().navigate(R.id.toSetupChildDetailFragment, RegisterChildDetailFragment.createArgs(position, mListChild[position]))
    }
}

