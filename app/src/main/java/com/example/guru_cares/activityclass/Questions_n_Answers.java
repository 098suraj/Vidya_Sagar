package com.example.guru_cares.activityclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.guru_cares.R;
import com.example.guru_cares.customadapters.question_custom_adapter;

public class Questions_n_Answers extends AppCompatActivity {
    RecyclerView questions;
    question_custom_adapter adapter;
    String quizsubject,timeduration,facultyname;
    int nquestions;

    String schoolname, gradecode;
    String username , userid, gradename, sectionname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_nanswers);
        nquestions = Integer.parseInt(getIntent().getStringExtra("nquestions"));
        quizsubject = getIntent().getStringExtra("quizsubject");
        schoolname = getIntent().getStringExtra("schoolname");
        gradecode = getIntent().getStringExtra("gradecode");
        facultyname = getIntent().getStringExtra("facultyname");
        username = getIntent().getStringExtra("username");
        userid = getIntent().getStringExtra("userid");
        gradename = getIntent().getStringExtra("gradename");
        sectionname = getIntent().getStringExtra("sectionname");

        questions = (RecyclerView) findViewById(R.id.recycler_questions);
        questions.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
        adapter = new question_custom_adapter(this,nquestions,quizsubject,schoolname,gradecode,username,userid,gradename,sectionname);
        questions.setAdapter(adapter);


    }
}