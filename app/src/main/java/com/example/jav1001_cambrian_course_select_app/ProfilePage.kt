package com.example.jav1001_cambrian_course_select_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.jav1001_cambrian_course_select_app.model.Student
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken


class ProfilePage : AppCompatActivity() {
    // created global variable studentList and student model
    var studentList:ArrayList<Student>? = ArrayList<Student>()
    var student:Student? = Student()
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_page)

        // Linked views created before .xml file
        val fullNameEditText: EditText? = findViewById(R.id.edit_full_name)
        val emailEditText: EditText? = findViewById(R.id.edit_email)
        val passwordEditText: EditText? = findViewById(R.id.edit_password)
        val programOfStudyEditText: EditText? = findViewById(R.id.edit_program_of_study)
        val changeMode: Switch? = findViewById(R.id.change_mode)
        val studentNumberTextView: TextView? = findViewById(R.id.student_number)
        val saveButton: TextView? = findViewById(R.id.save)

        val actionbar = supportActionBar
        // We changed actionbar title
        actionbar!!.title = "Profile"

        // Actionbar activate code
        actionbar.setDisplayHomeAsUpEnabled(true)

        // Created shared pref file course-app
        val sharedPref = getSharedPreferences("course-app", Context.MODE_PRIVATE)
        // Defined gson. Gson using for model serializable
        val gson = Gson()

        // get data to shared pref before it created
        val students=sharedPref.getString("students","").toString()
        val studentNumber=sharedPref.getString("studentNumber","").toString()
        val darkMode=sharedPref.getBoolean("darkMode",false)

        // assign dark mode data to switch the data get it from shared pref
        changeMode!!.isChecked = darkMode
        // defined gson item type as a ArrayList<Student>
        val itemType = object : TypeToken<ArrayList<Student>>() {}.type
        // get model list of data using gson and assign to list
        studentList = gson.fromJson<ArrayList<Student>>(students, itemType)

        // searching list inside the student number and assign to student object
        student=studentList!!.firstOrNull { it.studentNumber == studentNumber }

        // student information set textView and editText
        studentNumberTextView!!.text = student!!.studentNumber
        fullNameEditText!!.setText(student!!.fullName)
        emailEditText!!.setText(student!!.email)
        passwordEditText!!.setText(student!!.password)
        programOfStudyEditText!!.setText(student!!.programOfStudy)


        saveButton!!.setOnClickListener {
            //  Get data from edittext and assign to student information
            student!!.fullName = fullNameEditText.text.toString()
            student!!.email = emailEditText.text.toString()
            student!!.password = passwordEditText.text.toString()
            student!!.programOfStudy = programOfStudyEditText.text.toString()
            // create json format for new studentList
            val json = gson.toJson(studentList)
            val editor = sharedPref.edit()
            // save the json shared pref
            editor.putString("students",json)
            editor.apply()
            // every process is done routing new activity clas
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Switch button changed function
        changeMode!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                // if switch button isChecked we changed dark mode
                // add to shared pref dark mode key of true data
                val editor = sharedPref.edit()
                editor.putBoolean("darkMode",true)
                editor.apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }else{
                // if switch button is not checked we changed light mode
                // add to shared pref dark mode key of false data
                val editor = sharedPref.edit()
                editor.putBoolean("darkMode",false)
                editor.apply()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }





    }

    // Action bar back button function.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onContextItemSelected(item)
    }




}