package com.example.jav1001_cambrian_course_select_app.model

class User {
    var name:String=""
    var email:String=""
    var programOfStudy:String=""


    constructor(){}
    constructor(name:String,email:String,programOfStudy:String){
        this.name=name
        this.email=email
        this.programOfStudy=programOfStudy
    }
    fun getUser(): User{
        val user:User=User("Ertugrul","test@gmail.com","Mobile Application Development")
        return  user
    }
}