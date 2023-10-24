package com.example.calcageinmin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDayPicker)
        btnDatePicker.setOnClickListener{
            Toast.makeText(this, "Picking date", Toast.LENGTH_SHORT).show()
            showDatePickerDialog()
        }
    }

    private fun showDatePickerDialog() {
        val dateTextView : TextView = findViewById(R.id.date)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        //Function to display the date and age in days
        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                dateTextView.text = "$selectedDay/${selectedMonth+1}/$selectedYear"

                val currentDate = Calendar.getInstance()
                val daysDifference = (selectedDate.timeInMillis - currentDate.timeInMillis) / (1000 * 60 * 60 * 24) *-1

                // Display the number of days
                resultTextView.text = "$daysDifference"
            }, year, month, day)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() // Prevent choosing future dates
        datePickerDialog.show()
    }

}

