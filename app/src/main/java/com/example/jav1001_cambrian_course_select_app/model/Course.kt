package com.example.jav1001_cambrian_course_select_app.model

class Course {
    lateinit var courseCode:String
    lateinit var name:String
    lateinit var description:String
    lateinit var professor:String


    constructor(){}
    constructor(courseCode:String,name:String,description:String,professor:String){
        this.courseCode=courseCode
        this.name=name
        this.description=description
        this.professor=professor
    }

    public  fun getCourseList(): List<Course>{
        var courseList =mutableListOf<Course>()
        courseList.add(Course("WEB-1001","App Development for Web","In this course, students will discover the core concepts of developing software for the web. Students will use a\n" +
                "variety of programming tools, design elements, data types, controls, objects, and browsers","Brent Ritchie"))
        courseList.add(Course("JAV-1001","App Development for Android","In this course, students will explore the core concepts of developing software for the Android platform. Students\n" +
                "will use a variety of programming tools, data types, controls, objects, and emulators.","Graham Gibson"))
        courseList.add(Course("ISP-1002","App Development for iOS","In this course, students will learn the core concepts of developing software for the iOS platform. Students will use\n" +
                "a variety of programming tools, data types, controls, objects, and emulators","Joshua Van Der Most"))
        courseList.add(Course("DBA-1000","Structured Data Management","In this course, students will explore fundamental concepts in data and information management. The course\n" +
                "content centers on the essential skills of identifying organizational requirements, modelling requirements using\n" +
                "conceptual data modelling techniques, converting conceptual models into relational data models, and verifying\n" +
                "structural characteristics with normalization techniques","Samay Bhavsar"))
        courseList.add(Course("BTA-1002","Foundations of B.A","In this course, students will define the role of the IT Business Analyst. This course is an overview of the\n" +
                "extensiveness of the Business Analysis profession","Saifur Rahman"))
        return courseList
    }

}