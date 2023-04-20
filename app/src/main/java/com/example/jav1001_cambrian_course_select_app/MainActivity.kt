/*
    NAME: ERTUGRUL SAHIN , JOHN OLAYENI , LEVI MAXWELL
    STUDENT NUMBER : A00270022, A00260853 , A00263436
    DESCRIPTION : The project is a mobile application that allows students to choose a course.
    Students must be a register of the system and log in. After logging in, he can see and delete course sages.
    Students can update their own knowledge in the system. In the application, it can switch to the dark mode selection.

    Using the application

    1. Create account in the system
    2. Login your account
    3. Student can delete courses
    4. Student can update profile information
    5. Student can change dark mode

    Libraries used
    - Shared preferences
    - Glide
    - Gson

    App functionality
    - All models can be able to convert json format and read json format with Glide
    - Adding data to shared pref
    - After login save current user in the shared pref
    - Save dark mode status in the shared pref
    - Delete course/courses
    - Update student information in the system
 */

package com.example.jav1001_cambrian_course_select_app

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.jav1001_cambrian_course_select_app.adapter.CourseAdapter
import com.example.jav1001_cambrian_course_select_app.model.Course
import com.example.jav1001_cambrian_course_select_app.model.Student

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Linked views created before .xml file
        var todoListView: ListView?=findViewById(R.id.courseList)
        // Since we want to customize the item layout in Listview, we first defined the adapter
        // Get courseList created in the course model class
        var adapter=CourseAdapter(this,Course().getCourseList())
        // We connected listview to adapter
        todoListView?.adapter=adapter
        // we used it to update the information on the screen
        adapter.notifyDataSetChanged()

        val actionbar = supportActionBar
        // Changed actionbar title
        actionbar!!.title = "Course List"
    }

    // if the user click button finish the application
    override fun  onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

    //  Connect the settings menu_item.xml file to MainActivity
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // We connect the settings menu_item.xml file to MainActivity
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            // We click on the menus whose names we have given in the menu_item.xml file.
            R.id.profile -> {
                // It works when the profile menu is clicked.
                // Routing the new activity we have created.
                val intent = Intent(this, ProfilePage::class.java)
                startActivity(intent)
                true
            }
            R.id.logout ->{
                // It works when the logout menu is clicked.
                // Remove student number in the shared pref and routing landing page
                val sharedPref = getSharedPreferences("course-app", Context.MODE_PRIVATE)
                val editor = sharedPref.edit()
                editor.remove("studentNumber").apply()
                val intent = Intent(this, LandingPage::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}