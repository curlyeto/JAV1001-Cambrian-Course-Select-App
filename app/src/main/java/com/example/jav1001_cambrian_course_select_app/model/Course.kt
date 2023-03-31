package com.example.jav1001_cambrian_course_select_app.model

class Course {
    var courseCode:Int=0
    var name:String=""
    var description:String=""
    var professor:String=""


    constructor(){}
    constructor(courseCode:Int,name:String,description:String,professor:String){
        this.courseCode=courseCode
        this.name=name
        this.description=description
        this.professor=professor
    }
}