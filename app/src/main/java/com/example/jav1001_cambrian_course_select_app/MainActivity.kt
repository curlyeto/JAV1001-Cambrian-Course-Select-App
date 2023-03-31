package com.example.jav1001_cambrian_course_select_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.jav1001_cambrian_course_select_app.adapter.CourseAdapter
import com.example.jav1001_cambrian_course_select_app.model.Course

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var todoListView: ListView?=findViewById(R.id.courseList)
        // Since we want to customize the item layout in Listview, we first defined the adapter
        var adapter=CourseAdapter(this,Course().getCourseList())
        // We connected listview to adapter
        todoListView?.adapter=adapter
        // we used it to update the information on the screen
        adapter.notifyDataSetChanged()
    }
}