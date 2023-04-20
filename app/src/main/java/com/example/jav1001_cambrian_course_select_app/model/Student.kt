package com.example.jav1001_cambrian_course_select_app.model

class Student  {
    lateinit var fullName:String
    lateinit var studentNumber:String
    lateinit var email:String
    lateinit var password:String
    lateinit var programOfStudy:String



    constructor(){}
    constructor(name:String,studentNumber:String,email:String,password:String,programOfStudy:String){
        this.fullName=name
        this.studentNumber=studentNumber
        this.fullName=name
        this.email=email
        this.password=password
        this.programOfStudy=programOfStudy
    }
}