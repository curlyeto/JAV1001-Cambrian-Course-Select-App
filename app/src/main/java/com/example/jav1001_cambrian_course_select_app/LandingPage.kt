package com.example.jav1001_cambrian_course_select_app

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.jav1001_cambrian_course_select_app.Login.LoginPage


// This page created for checking current user into the system and dark mode status
class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)
        // Linked views created before .xml file
        val sharedPref = getSharedPreferences("course-app", Context.MODE_PRIVATE)
        val studentNumber=sharedPref.getString("studentNumber","").toString()


        val actionbar = supportActionBar
        // We changed actionbar title
        actionbar!!.title = "Landing Page"
        // Checking dark mode status
        val darkMode=sharedPref.getBoolean("darkMode",false)
        if (darkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        // checking student number is empty or not
        if (studentNumber.isNotEmpty()){
            // it is not empty routing mainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            // it is empty routing login page
            val intent = Intent(this, LoginPage::class.java)
            startActivity(intent)
        }

    }
}