package com.example.jav1001_cambrian_course_select_app.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jav1001_cambrian_course_select_app.R
import com.example.jav1001_cambrian_course_select_app.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*


class RegisterPage : AppCompatActivity() {

    var studentList:ArrayList<Student>? = ArrayList<Student>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        // Linked views created before .xml file
        val fullName: EditText? = findViewById(R.id.full_name)
        val email: EditText? = findViewById(R.id.email)
        val password: EditText? = findViewById(R.id.password)
        val programOfStudy: EditText? = findViewById(R.id.program_of_study)
        val registerButton: Button? = findViewById(R.id.register)


        val actionbar = supportActionBar
        // We changed actionbar title
        actionbar!!.title = "Register"

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        // Created shared pref file course-app
        val sharedPref = getSharedPreferences("course-app",Context.MODE_PRIVATE)
        // Defined gson. Gson using for model serializable
        val gson = Gson()


        registerButton?.setOnClickListener {
            // validation for inputs
            if (validation(fullName,email,password,programOfStudy)){
                // get data to shared pref before it created
                val students = sharedPref.getString("students","").toString()
                // defined gson item type as a ArrayList<Student>
                val itemType = object : TypeToken<ArrayList<Student>>() {}.type
                // get model list of data using gson and assign to list
                studentList = gson.fromJson<ArrayList<Student>>(students, itemType)
                // crating new function for add user in the system
                addStudent(fullName, email, password, programOfStudy, gson, sharedPref)
            }else{
                Toast.makeText(applicationContext,"Please fill all field", Toast.LENGTH_LONG).show()
            }

        }

    }
    // validation for all inputs
    private fun validation(
        fullName: EditText?,
        email: EditText?,
        password: EditText?,
        programOfStudy: EditText?
    ):Boolean {
        if (fullName?.text.toString() == "") {
            return false
        }
        if (email?.text.toString() == "") {
            return false
        }
        if (password?.text.toString() == "") {
            return false
        }
        if (programOfStudy?.text.toString() == "") {
            return false
        }
        return  true
    }

    // Action bar back button function.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }

    // Adding new student in the system. And saving data shared pref
    private fun addStudent(
        fullName: EditText?,
        email: EditText?,
        password: EditText?,
        programOfStudy: EditText?,
        gson: Gson,
        sharedPref: SharedPreferences
    ) {
        // Creating random student text
        var studentNumber:String=createStudentNumber()
        // adding all data coming from inputs and student number into the student object
        val student=Student(fullName!!.text.toString(),studentNumber,email?.text.toString(),password?.text.toString(),programOfStudy?.text.toString())
        // student object adding to studentList
        studentList?.add(student)
        // create json format for new studentList
        val json = gson.toJson(studentList)
        val editor = sharedPref.edit()
        // save the json shared pref
        editor.putString("students",json)
        editor.apply()
        // return student number inside to toast message
        Toast.makeText(applicationContext,"Register is success. Student number ${studentNumber}", Toast.LENGTH_LONG).show()
        // every process is done routing new login page
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)
    }

    // The method give us random string. Checking string has valid before if it's crete new random string
    private fun createStudentNumber(): String {
        val ALLOWED_CHARACTERS = "0123456789"
        val random = Random()
        val sb = StringBuilder(6)

        for (i in 0 until 6)
            sb.append(ALLOWED_CHARACTERS[random.nextInt(ALLOWED_CHARACTERS.length)])

        val randomString:String = "CA"+sb.toString()

        if (studentList!=null){
            val student = studentList!!.firstOrNull { it.studentNumber == randomString }
            if (student != null){
                createStudentNumber()
            }
        }else{
            studentList = ArrayList<Student>()
        }
        return randomString
    }
}