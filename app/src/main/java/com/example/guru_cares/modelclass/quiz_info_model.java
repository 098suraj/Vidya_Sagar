package com.example.guru_cares.modelclass;

public class quiz_info_model {

    String quizsubject;
    int nquestions;
    String type;
    String facultyname;
    int timeduration;

    public quiz_info_model()
    {

    }

    public quiz_info_model(String quizsubject, String type, String facultyname, int nquestions, int timeduration) {
        this.quizsubject = quizsubject;
        this.type = type;
        this.timeduration = timeduration;
        this.facultyname = facultyname;
        this.nquestions = nquestions;

    }


    public int getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(int timeduration) {
        this.timeduration = timeduration;
    }

    public String getFacultyname() {
        return facultyname;
    }

    public void setFacultyname(String facultyname) {
        this.facultyname = facultyname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNquestions() {
        return nquestions;
    }

    public void setNquestions(int nquestions) {
        this.nquestions = nquestions;
    }

    public String getQuizsubject() {
        return quizsubject;
    }

    public void setQuizsubject(String quizsubject) {
        this.quizsubject = quizsubject;
    }


}
