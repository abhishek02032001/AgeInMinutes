 package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

 class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { View ->
            showDatePicker(View)
        }
    }

    fun showDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val selectedDate = "$dayOfMonth/${month + 1}/${year}"
            DOB.setText(selectedDate)
            DOB.setPadding(25, 10, 25 ,10)


            val sdf = SimpleDateFormat("dd/MM/yyyy")

            val dateShowed = sdf.parse(selectedDate)
            val selectedDateTime = dateShowed.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateTime = currentDate.time / 60000

            val ageInMinutes = currentDateTime - selectedDateTime

            AgeInMinutes.setText(ageInMinutes.toString())
            AgeInMinutes.setPadding(25,10,25,10)
        }, year, month, day)

        dpd.datePicker.setMaxDate(Date().time)
        dpd.show()
    }
}