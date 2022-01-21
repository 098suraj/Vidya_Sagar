package com.example.guru_cares.modelclass;

public class grade_model {

    public String gradename;
    public String sections;

    public grade_model()
    {

    }

    public grade_model(String gradename)
    {
        this.gradename = gradename;

    }


    public grade_model(String sections , String gradename)
    {
        this.sections = sections;
        this.gradename = gradename;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sectionname) {
        this.sections = sectionname;
    }

    public String getGradename() {
        return gradename;
    }

    public void setGradename(String gradename) {
        this.gradename = gradename;
    }


}