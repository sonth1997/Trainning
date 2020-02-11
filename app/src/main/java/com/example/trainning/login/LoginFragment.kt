package com.example.trainning.login

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainning.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        check()

    }

    fun check(){
        btnLogin.setOnClickListener {
            if (edtUserName.text.toString().isEmpty() || edtPassWork.text.toString().isEmpty()){
                Toast.makeText(activity,"user name or pass work is empty",Toast.LENGTH_SHORT).show()
            }else{
                findNavController().navigate(R.id.action_login_to_main)
            }
        }
    }

}