package com.example.jav1001_cambrian_course_select_app.Login

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.jav1001_cambrian_course_select_app.MainActivity
import com.example.jav1001_cambrian_course_select_app.ProfilePage
import com.example.jav1001_cambrian_course_select_app.R
import com.example.jav1001_cambrian_course_select_app.model.Student
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

class LoginPage : AppCompatActivity() {
    // created global variable studentList
    var studentList:ArrayList<Student>? = ArrayList<Student>()
    @SuppressLint("MissingInflatedId", "CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        // Linked views created before .xml file
        val registerText: TextView?=findViewById(R.id.registerText)
        val studentNumber: EditText?=findViewById(R.id.student_number)
        val password: EditText?=findViewById(R.id.login_password)
        val loginButton: Button?=findViewById(R.id.login)


        val actionbar = supportActionBar
        // We changed actionbar title
        actionbar!!.title = "Login"

        // Created shared pref file course-app
        val sharedPref = getSharedPreferences("course-app",Context.MODE_PRIVATE)
        // Defined gson. Gson using for model serializable
        val gson = Gson()

        // routing register page when the user click Register text
        registerText?.setOnClickListener {
            val intent = Intent(this, RegisterPage::class.java)
            startActivity(intent)
        }

        loginButton?.setOnClickListener {
            // get data to shared pref before it created
            val students=sharedPref.getString("students","").toString()
            // defined gson item type as a ArrayList<Student>
            val itemType = object : TypeToken<ArrayList<Student>>() {}.type
            // get model list of data using gson and assign to list
            studentList = gson.fromJson<ArrayList<Student>>(students, itemType)

            // check the studentList is null or not
            if (studentList != null){
                // checking the student number and password is valid returning student object
                val student = studentList!!.firstOrNull { it.studentNumber == studentNumber!!.text.toString() && it.password == password!!.text.toString() }
                // student object is not null login is success
                if (student != null){
                    // save student number shared pref, the data using for current user
                    val editor = sharedPref.edit()
                    editor.putString("studentNumber",studentNumber!!.text.toString())
                    editor.apply()
                    // every process is done routing new activity clas
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    // if the user enter wrong information show toast message
                    Toast.makeText(applicationContext,"Student number or password incorrect",Toast.LENGTH_LONG).show()
                }
            }else{
                Log.d("TAG","Students list empty")
            }

        }


    }
}