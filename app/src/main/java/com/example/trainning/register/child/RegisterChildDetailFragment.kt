package com.example.trainning.register.child

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.trainning.R
import com.example.trainning.data.PreferenceUtils
import com.example.trainning.models.Child
import com.example.trainning.models.GENDER
import com.example.trainning.models.SICK
import com.google.gson.Gson
import com.raywenderlich.android.validatetor.ValidateTor
import kotlinx.android.synthetic.main.item_register_child.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RegisterChildDetailFragment : Fragment() {

    private lateinit var validateTor: ValidateTor
    private var isActive = false
    private var position: Int = 0
    companion object {
        private const val POSITION = "POSITION"
        private const val CHILD = "CHILD"
        fun createArgs(position: Int, child: Child): Bundle {
            val bundle = Bundle()
            bundle.putInt(POSITION, position)
            bundle.putSerializable(CHILD, child)
            return bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.item_register_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        validateTor = ValidateTor()
        super.onViewCreated(view, savedInstanceState)
        initViews()
        onClick()
        onSetData()
    }

    private fun initViews() {
        arguments?.let {
            position = it.getInt(POSITION)
        }
    }

    private fun onClick() {

        btnOk.setOnClickListener {
            validate(edtUser)
            onSave()
        }
        edtTime.setOnClickListener {
            dateOfBirth()
        }
        button.setOnClickListener{

            button.isActivated = !it.isActivated
        }
        button2.setOnClickListener{
            button2.isActivated = !it.isActivated
        }
        button3.setOnClickListener{
            button3.isActivated = !it.isActivated

        }
        button4.setOnClickListener {
            button4.isActivated = !it.isActivated

        }
        button5.setOnClickListener {
            button5.isActivated = !it.isActivated

        }
        button6.setOnClickListener {
            button6.isActivated = !it.isActivated

        }
        button7.setOnClickListener {
            button7.isActivated = !it.isActivated

        }
        button8.setOnClickListener {
            button8.isActivated = !it.isActivated

        }
        button9.setOnClickListener {
            button9.isActivated = !it.isActivated
            isActive = !isActive

        }
    }
    private fun validate(editText: EditText) {
        val str = editText.text.toString()
        validateTor.apply {
            if (edtUser.text.toString().isEmpty()) {
                edtUser.error = ""
                showAlertDialog()
            } else {
                if (edtComment.text.toString().isEmpty()) {
                    edtComment.error = ""
                    showAlertDialog()
                } else {
                    when {
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
                        }
                        validateTor.hasAtleastOneSpecialCharacter(str) -> {
                            edtUser.error = ""
                            showAlertDialogSpecialCharacter()
                        }
                        validateTor.hasAtleastOneDigit(str) -> {
                            edtUser.error = ""
                            showAlertDialogNumber()
                        }
                        !button.isActivated && !button2.isActivated && !button3.isActivated -> {
                            checkButtonNull()
                        }
                        button.isActivated && button2.isActivated ||
                                button.isActivated && button3.isActivated ||
                                button2.isActivated && button3.isActivated ||
                                button.isActivated && button2.isActivated && button3.isActivated -> {
                            checkButton()
                        }
                        !button4.isActivated && !button5.isActivated && !button6.isActivated
                                && !button7.isActivated && !button8.isActivated && !button9.isActivated -> {
                            checkButtonSick()
                        }
                        button.isActivated || button2.isActivated || button3.isActivated ||
                                button4.isActivated || button5.isActivated || button6.isActivated ||
                                button7.isActivated || button8.isActivated || button9.isActivated -> {
                            findNavController().popBackStack()
                        }
                        else -> {
                            findNavController().popBackStack()
                        }
                    }
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
    private fun checkButtonNull() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Vui lòng chọn giới tính")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }
    private fun checkButton() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Bạn chỉ được chọn một giới tính")
        builder.setCancelable(false)
        builder.setPositiveButton(
            "ok"
        ) { _, _ -> }
        val alertDialog = builder.create()
        alertDialog.show()
    }
    private fun checkButtonSick() {
        val builder = AlertDialog.Builder(context)
        builder.setMessage("Vui lòng chọn tên bệnh")
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
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, day)
                edtTime.setText(getReadDate(calendar))
            }
        context?.let { val timeDialog = DatePickerDialog(it, setDateListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
            timeDialog.datePicker.maxDate = System.currentTimeMillis()
            timeDialog.show()
        }
    }

    private fun getReadDate(calendar: Calendar): String {
        val df = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return df.format(calendar.time)
    }
    @SuppressLint("CommitPrefEdits", "ApplySharedPref")
    private fun onSave() {
        val preferenceUtils = PreferenceUtils(context, Gson())
        val list = preferenceUtils.getListChild()
        val obj = list?.get(position)
        obj?.let {
            it.note = edtComment.text.toString()
            it.name = edtUser.text.toString()
            it.time = edtTime.text.toString()
            it.gender = getSelectGender()
            it.sick = getSelectSick()
            list[this.position] = it
        }

        preferenceUtils.putListChild(list as ArrayList<Child>)
    }

    private fun getSelectGender(): GENDER {
        return when(true){
            button.isActivated -> GENDER.MALE
            button2.isActivated -> GENDER.FEMALE
            button3.isActivated -> GENDER.OTHER
            else -> GENDER.MALE
        }
    }
    private fun getSelectSick(): SICK {
        return when(true){
            button4.isActivated -> SICK.ADHD
            button5.isActivated -> SICK.LD
            button6.isActivated -> SICK.MR
            button7.isActivated -> SICK.HATHOI
            button8.isActivated -> SICK.DAUHONG
            button9.isActivated -> SICK.HO
            else -> SICK.ADHD
        }
    }

    private fun onSetData(){
        val obj = PreferenceUtils(context, Gson()).getListChild()?.get(position)
        edtUser.setText(obj?.name.toString())
        edtTime.setText(obj?.time.toString())
        edtComment.setText(obj?.note.toString())
        obj?.let {
            when(it.gender){
                GENDER.MALE-> button.isActivated = true
                GENDER.FEMALE -> button2.isActivated = true
                GENDER.OTHER -> button3.isActivated = true
                else -> {}
            }
        }
        obj.let {
            when (it?.sick){
                SICK.ADHD -> button4.isActivated = true
                SICK.LD -> button5.isActivated = true
                SICK.MR -> button6.isActivated = true
                SICK.HO -> button9.isActivated = true
                SICK.HATHOI -> button7.isActivated = true
                SICK.DAUHONG -> button8.isActivated = true
            else -> {}
            }
        }
    }
}