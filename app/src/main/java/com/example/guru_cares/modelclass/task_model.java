package com.example.guru_cares.modelclass;

public class task_model {

   public String quizsubject;
   public String type;
   public int nquestions;
   public int timeduration;

   public task_model()
   {

   }

    public task_model(String quizsubject, String type, int nquestions, int timeduration) {
        this.quizsubject = quizsubject;
        this.type = type;
        this.nquestions = nquestions;
        this.timeduration = timeduration;

    }



    public int getTimeduration() {
        return timeduration;
    }

    public void setTimeduration(int timeduration) {
        this.timeduration = timeduration;
    }

    public int getNquestions() {
        return nquestions;
    }

    public void setNquestions(int nquestions) {
        this.nquestions = nquestions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuizsubject() {
        return quizsubject;
    }

    public void setQuizsubject(String quizsubject) {
        this.quizsubject = quizsubject;
    }

}
