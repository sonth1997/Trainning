package com.example.trainning.register.user

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainning.R
import com.example.trainning.data.PreferenceUtils
import com.example.trainning.models.Child
import com.google.gson.Gson
import com.raywenderlich.android.validatetor.ValidateTor
import kotlinx.android.synthetic.main.fragment_register_user.*
import kotlinx.android.synthetic.main.fragment_register_user.edtComment
import kotlinx.android.synthetic.main.fragment_register_user.edtTime
import kotlinx.android.synthetic.main.fragment_register_user.edtUser
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.YEAR
import kotlin.collections.ArrayList

class RegisterUserFragment : Fragment() {

    private lateinit var validateTor: ValidateTor
    private lateinit var spiner: Spinner
    private val REQUEST_GALLERY = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_user, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateTor = ValidateTor()
        spiner = Spinner(context)

        btnFinish.setOnClickListener {
            onSave()
            validatePasswordField(edtUser)
        }
        edtTime.setOnClickListener {
            dateOfBirth()
        }
        imgBack.setOnClickListener {
            back()
        }
        imgUpload.setOnClickListener {
            upLoadImage()
        }
        numberOfChildren()
        onSetData()
    }

    private fun validatePasswordField(editText: EditText) {
        val str = editText.text.toString()
        validateTor.apply {
            when {
                edtUser.text.toString().isEmpty() -> {
                    edtUser.error = ""
                    showAlertDialog()
                }
                edtComment.text.toString().isEmpty() -> {
                    edtComment.error = ""
                    showAlertDialog()
                }
                edtTime.text.toString().isEmpty() -> {
                    edtTime.error = ""
                    showAlertDialog()
                }
                edtComment.length() > 500 -> {
                    edtComment.error = ""
                    showAlertDialogNumberFive()
                }
                edtUser.length() > 10 -> {
                    edtUser.error = ""
                    showAlertDialogNumberTen()
                }// dau loi cho nao
                validateTor.hasAtleastOneSpecialCharacter(str) -> {
                    edtUser.error = ""
                    showAlertDialogSpecialCharacter()
                }
                validateTor.hasAtleastOneDigit(str) -> {
                    edtUser.error = ""
                    showAlertDialogNumber()
                }
                else -> {
                    findNavController().navigate(R.id.action_registerUserFragment_to_registerChildFragment)

                }
            }
        }
    }
    private fun showAlertDialogNumber() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Dữ liệu nhập không được là số")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun showAlertDialogNumberTen() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Dữ liệu nhập phải không được lớn hơn 10 kí tự")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun showAlertDialogSpecialCharacter() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Dữ liệu nhập không được có kí tự đặc biệt")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Lỗi! Hãy nhập dữ liệu")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun showAlertDialogNumberFive() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Không được nhập quá 500 kí tự")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun dateOfBirth() {
        val calendar: Calendar = Calendar.getInstance()
        val setDateListener: DatePickerDialog.OnDateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, month, day ->
                calendar.set(YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                edtTime.setText(getReadDate(calendar))

            }
        context?.let {val timeDialog =DatePickerDialog(it, setDateListener, calendar.get(YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
            timeDialog.datePicker.maxDate = System.currentTimeMillis()
            timeDialog.show()

        }
    }

     private fun getReadDate(calendar: Calendar): String {
        val df = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return df.format(calendar.time)
    }


    private fun numberOfChildren() {
        val number = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9")
        val adapter = context?.let { ArrayAdapter(it, android.R.layout.simple_list_item_1, number) }
        spinerNumber.adapter = adapter

    }

    private fun upLoadImage() {
        val gallery = Intent(Intent.ACTION_GET_CONTENT)
        gallery.type = "image/*"
        val choose = Intent.createChooser(gallery, "Choose image")
        startActivityForResult(choose, REQUEST_GALLERY)
    }

    private fun back() {
        imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_registerUserFragment_to_firstFragment)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try {
                val b = MediaStore.Images.Media.getBitmap(context?.contentResolver, data!!.data)
                imgUpload.setImageBitmap(b)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun onSave() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val countChild = spinerNumber.selectedItem.toString().toInt()
        val editor = sharedPref?.edit()
        editor?.putString("NAME",edtUser.text.toString())
        editor?.putString("DATE",edtTime.text.toString())
        editor?.putString("COMMENT",edtComment.text.toString())
        editor?.putInt("COUNT_CHILD", countChild)
        editor?.apply()

        onSaveListChild(countChild)
    }

    private fun onSaveListChild(countChild: Int) {
        val preferences = PreferenceUtils(context, Gson())
        val mList: ArrayList<Child> = arrayListOf()
        for (i in 0 until countChild){
            mList.add(Child(id = (i + 1)))
        }

        preferences.putListChild(mList)
    }

    private fun onSetData(){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val name = sharedPref?.getString("NAME","")
        val date = sharedPref?.getString("DATE","")
        val comment = sharedPref?.getString("COMMENT","")
        val countChild = sharedPref?.getInt("COUNT_CHILD", 0)?: 1
        edtUser.setText(name)
        edtTime.setText(date)
        edtComment.setText(comment)
        spinerNumber.setSelection(countChild - 1)
    }
}


