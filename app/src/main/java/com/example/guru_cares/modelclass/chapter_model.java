package com.example.guru_cares.modelclass;

public class chapter_model {


    private String chaptername;
   // private String chaptertitle;
    private String chapterdescription;
    private String filelink;


    public chapter_model()
    {

    }


    public chapter_model(String chaptername , String chapterdescription, String filelink)
    {
        this.chaptername = chaptername;
        this.filelink = filelink;
        //this.chaptertitle = chaptertitle;
        this.chapterdescription = chapterdescription;

    }

    public String getFilelink() {
        return filelink;
    }

    public void setFilelink(String filelink) {
        this.filelink = filelink;
    }

    public String getChapterdescription() {
        return chapterdescription;
    }

    public void setChapterdescription(String chapterdescription) {
        this.chapterdescription = chapterdescription;
    }

   /* public String getChaptertitle() {
        return chaptertitle;
    }

    public void setChaptertitle(String chaptertitle) {
        this.chaptertitle = chaptertitle;
    }*/

    public chapter_model(String chaptername ) {
        this.chaptername = chaptername;
    }

    public String getChaptername() {
        return chaptername;
    }

    public void setChaptername(String chaptername) {
        this.chaptername = chaptername;
    }
}
