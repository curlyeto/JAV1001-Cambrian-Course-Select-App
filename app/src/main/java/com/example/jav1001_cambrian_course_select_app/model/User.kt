package com.example.jav1001_cambrian_course_select_app.model

class User {
    lateinit var name:String
    lateinit var email:String
    lateinit var programOfStudy:String
    lateinit var selectedCourse:List<Course>

    constructor(){}
    constructor(name:String,email:String,programOfStudy:String){
        this.name=name
        this.email=email
        this.programOfStudy=programOfStudy
    }
    fun getUser(): User{
        return  User("Ertugrul","test@gmail.com","Mobile Application Development")
    }
}