package com.example.jav1001_cambrian_course_select_app.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView
import com.example.jav1001_cambrian_course_select_app.R
import com.example.jav1001_cambrian_course_select_app.model.Course

class CourseAdapter(var activity: Activity, private var items:List<Course>): BaseAdapter() {
    // The task of this class is to connect the layout we created as custom to this class and to call the views here and to do the operations.
    private class  ViewHolder(row: View?){
        // Here we define all the videos that we use in the row view that we created as custom.
        var name: TextView?=null
        var courseCode: TextView?=null
        var professor: TextView?=null
        var description: TextView?=null
        init {
            this.name=row?.findViewById<TextView>(R.id.name)
            this.courseCode=row?.findViewById<TextView>(R.id.course_code)
            this.professor=row?.findViewById<TextView>(R.id.professor)
            this.description=row?.findViewById<TextView>(R.id.description)
        }
    }
    override fun getCount(): Int {
        // Return the total number of elements of the list
        return  items.size
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Course {
        // Return every item in listview as a model
        return items[position]
        TODO("Not yet implemented")
    }

    override fun getItemId(position: Int): Long {
        // It freezes the id of the item in listview
        return  position.toLong()
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // We connect the custom layout we created here to the view holder. Because we defined views in view holder
        val view: View?
        val viewHolder:ViewHolder
        if (convertView==null){
            val inflater= activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view=inflater.inflate(R.layout.course_item,null)
            viewHolder=ViewHolder(view)
            view.tag=viewHolder
        }else{
            view=convertView
            viewHolder=view.tag as ViewHolder
        }
        viewHolder.name?.text=items[position].name
        viewHolder.description?.text=items[position].description
        viewHolder.courseCode?.text=items[position].courseCode
        viewHolder.professor?.text=items[position].professor

        return  view as View
        TODO("Not yet implemented")

    }
}