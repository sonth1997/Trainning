package com.example.trainning.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.trainning.R
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.item_main.*
import java.util.*


class MainFragment : Fragment(),MainAdapter.onClickItemMain {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
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
        Random()
        val color = Color.rgb(Random().nextInt(99), Random().nextInt(99), Random().nextInt(99))
        btnItemMain.setBackgroundColor(color)
    }

}