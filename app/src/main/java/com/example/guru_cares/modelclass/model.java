package com.example.guru_cares.modelclass;

public class model {


    private int subjectimage;
    private String subjectname;
    private String facultyname;
    private String enrollbtn;

    public model(int subjectimage, String subjectname, String facultyname, String enrollbtn) {

        this.subjectimage = subjectimage;
        this.subjectname = subjectname;
        this.facultyname = facultyname;
        this.enrollbtn = enrollbtn;

    }

    public String getEnrollbtn() {
        return enrollbtn;
    }

    public void setEnrollbtn(String enrollbtn) {
        this.enrollbtn = enrollbtn;
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public void setSubjectname(String subjectname) {
        this.subjectname = subjectname;
    }

    public int getSubjectimage() {
        return subjectimage;
    }

    public void setSubjectimage(int subjectimage) {
        this.subjectimage = subjectimage;
    }


}
