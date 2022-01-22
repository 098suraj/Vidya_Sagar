package com.example.guru_cares.customadapters;

public class Mainviewmodel {
    Integer percentage;
    String SubjectName;

    public Mainviewmodel(Integer percentage,String SubjectName){
        this.percentage = percentage;
        this.SubjectName = SubjectName;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

}
