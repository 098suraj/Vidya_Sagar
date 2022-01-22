package com.example.guru_cares.activityclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.guru_cares.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

public class FinalResult extends AppCompatActivity {
    CircularProgressBar circularProgressBar;
    TextView textView;
    int correct, wrong;
    LinearLayout scoreshare;
    int size;

    String username, userid, gradename, sectionname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_result);

        username = getIntent().getStringExtra("username");
        userid = getIntent().getStringExtra("userid");
        gradename = getIntent().getStringExtra("gradename");
        sectionname = getIntent().getStringExtra("sectionname");


        correct = getIntent().getIntExtra("correct", 0);//default value is here bcs if no value is stored in correct so default value will be stored for correct
        wrong = getIntent().getIntExtra("wrong", 0);
        size = getIntent().getIntExtra("size", 0);

        circularProgressBar = findViewById(R.id.circularProgressBar);
        textView = findViewById(R.id.scores);
        scoreshare = findViewById(R.id.sharescore);

        circularProgressBar.setScrollBarSize(2);
        circularProgressBar.setProgress(correct);

        textView.setText(correct + "/"+ size);

        scoreshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nI scored " + correct + " out of" + size + " marks.\n You May also try\n";
                    shareMessage.toUpperCase();
                    //shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            onBackPressed();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        //this is only needed if you have specific things
        //that you want to do when the user presses the back button.
        // your specific things...
        super.onBackPressed();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("username", username);
        i.putExtra("userid", userid);
        i.putExtra("gradename", gradename);
        i.putExtra("sectionname", sectionname);
        startActivity(i);


    }

}