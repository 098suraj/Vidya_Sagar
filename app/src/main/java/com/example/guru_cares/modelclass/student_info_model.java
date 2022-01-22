package com.example.guru_cares.modelclass;

public class student_info_model {

    private String name;
    private String section;
    private String grade;
    private String rollno;
    private String backname;
    private String address;
    private String phone;

    public student_info_model()
    {

    }

    public student_info_model(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }


    public student_info_model(String name, String grade, String address, String backname, String phone) {
        this.name = name;
        this.grade = grade;
        this.address = address;
        this.phone = phone;
        this.backname = backname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBackname() {
        return backname;
    }

    public void setBackname(String backname) {
        this.backname = backname;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
