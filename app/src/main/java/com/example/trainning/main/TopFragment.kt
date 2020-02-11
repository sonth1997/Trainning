package com.example.trainning.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainning.R
import kotlinx.android.synthetic.main.fragment_category.*

class TopFragment : Fragment(),MainAdapter.onClickItemMain {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_top, container, false)
    }
    private lateinit var adapterMain: MainAdapter

    private var mdata:MutableList<Main> = mutableListOf(
        Main("First","hohohooh","đasdadsdsds","ádsdaadsads"),
        Main("First","hohohooh","đasdadsdsds","ádsdaadsads"),
        Main("First","hohohooh","đasdadsdsds","ádsdaadsads"),
        Main("First","hohohooh","đasdadsdsds","ádsdaadsads"),
        Main("First","hohohooh","đasdadsdsds","ádsdaadsads")
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapterMain = MainAdapter(this)
        adapterMain.setData(mdata)
        rcvCategory.adapter = adapterMain
    }
    override fun onClickItemMain(main: Main) {
    }
}