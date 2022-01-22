package com.example.guru_cares.activityclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guru_cares.R;
import com.example.guru_cares.modelclass.QuizModal;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import java.util.Collections;
import java.util.List;

import static com.example.guru_cares.Fragmentclass.quiz_info.quizModals;
//import static com.example.gurucares.fragmentclass..quizModals;

public class DashboardActivity extends AppCompatActivity {
    String username , userid, gradename, sectionname;
    private TextView questions, textbtnA, textbtnB, textbtnC, textbtnD;
    CardView cardOptA, cardOptB, cardOptC, cardOptD;
    List<QuizModal> allquestionlist;
    QuizModal quizModal;
    int correct = 0, wrong = 0, index = 0;
    TextView next;

    CountDownTimer countDownTimer;
    int timervalue = 20;
    LinearProgressIndicator linearProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        username = getIntent().getStringExtra("username");
        userid = getIntent().getStringExtra("userid");
        gradename = getIntent().getStringExtra("gradename");
        sectionname = getIntent().getStringExtra("sectionname");



        idmethod();

        next.setClickable(false);
//        next.setVisibility(View.INVISIBLE);
        allquestionlist = quizModals;
        Collections.shuffle(allquestionlist);//to get random questions in the quiz
        quizModal = quizModals.get(index);
        setDatatoViews();
        resetcolour();
        countDownTimer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timervalue = timervalue - 1;
                linearProgressIndicator.setProgress(timervalue);
            }

            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(DashboardActivity.this);
//                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setContentView(R.layout.time_out_dialog);
                dialog.show();

                dialog.findViewById(R.id.try_again).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DashboardActivity.this, DashboardActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }.start();











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





    private void idmethod() {
        linearProgressIndicator = findViewById(R.id.quest_timer);
        questions = findViewById(R.id.quest);
        textbtnA = findViewById(R.id.btnA);
        textbtnB = findViewById(R.id.btnB);
        textbtnC = findViewById(R.id.btnC);
        textbtnD = findViewById(R.id.btnD);

        cardOptA = findViewById(R.id.cardoA);
        cardOptB = findViewById(R.id.cardoB);
        cardOptC = findViewById(R.id.cardoC);
        cardOptD = findViewById(R.id.cardoD);

        next = findViewById(R.id.nextbtn);
    }


    private void setDatatoViews() {
        questions.setText(quizModal.getQuestion());
        textbtnA.setText(quizModal.getOption1());
        textbtnB.setText(quizModal.getOption2());
        textbtnC.setText(quizModal.getOption3());
        textbtnD.setText(quizModal.getOption4());
    }


    public void correctans(CardView cardView)
    {
//        next.setVisibility(View.VISIBLE);
        //Toast.makeText(getApplicationContext()," "+index, Toast.LENGTH_SHORT).show();
        cardView.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
        //correct++;
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index<quizModals.size()-1)
                {
                    correct++;
                    index++;
                    quizModal=quizModals.get(index);
                    setDatatoViews();
                    resetcolour();
                    enablebutton();
                }
                else
                {
                    correct++;
                    GameWon();
                }

                /*if(index==quizModals.size())
                {
                    //correct++;
                    GameWon();
                }*/

            }
        });
    }

    public void wrongans(CardView cardOptA)
    {
        //Toast.makeText(getApplicationContext()," "+index, Toast.LENGTH_SHORT).show();

        //Toast.makeText(getApplicationContext(), index, Toast.LENGTH_SHORT).show();
//        next.setVisibility(View.VISIBLE);
        cardOptA.setCardBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wrong++;
                /*if(index==quizModals.size())
                {
                    GameWon();
                }*/


                if(index<quizModals.size()-1)
                {
                    index++;
                    quizModal=quizModals.get(index);
                    setDatatoViews();
                    resetcolour();
                    enablebutton();
                }
                else
                {
                    //correct++;
                    GameWon();
                }



            }
        });
    }

    private void GameWon() {
        Intent intent = new Intent(DashboardActivity.this,FinalResult.class);
        intent.putExtra("correct",correct);
        intent.putExtra("wrong",wrong);
        intent.putExtra("size", quizModals.size());
        intent.putExtra("username", username);
        intent.putExtra("userid", userid);
        intent.putExtra("gradename", gradename);
        intent.putExtra("sectionname", sectionname);
        startActivity(intent);
    }

    public void enablebutton()
    {
        cardOptA.setClickable(true);
        cardOptB.setClickable(true);
        cardOptC.setClickable(true);
        cardOptD.setClickable(true);
    }
    public void dissablebutton()
    {
        cardOptA.setClickable(false);
        cardOptB.setClickable(false);
        cardOptC.setClickable(false);
        cardOptD.setClickable(false);
    }

    public void resetcolour()
    {
        cardOptA.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOptB.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOptC.setCardBackgroundColor(getResources().getColor(R.color.white));
        cardOptD.setCardBackgroundColor(getResources().getColor(R.color.white));
    }


    public void optAclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption1().equals(quizModal.getAnswer()))
        {
//            cardOptA.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index <= quizModals.size()-1)
            {
                correctans(cardOptA);
//                resetcolour();//some error

            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(cardOptA);

    }

    public void optBclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption2().equals(quizModal.getAnswer()))
        {
//            cardOptB.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index<=quizModals.size()-1)
            {
                correctans(cardOptB);
//                resetcolour();//some error

            }
            else
            {
                GameWon();
            }
        }
        else
            wrongans(cardOptB);

    }

    public void optCclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption3().equals(quizModal.getAnswer()))
        {
//            cardOptC.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index <= quizModals.size()-1)
            {
                correctans(cardOptC);
//                resetcolour();//some error

            }
            else
            {
                //GameWon();
            }
        }
        else
            wrongans(cardOptC);

    }

    public void optDclick(View view) {
        dissablebutton();
        next.setClickable(true);
//        next.setVisibility(View.VISIBLE);
        if(quizModal.getOption4().equals(quizModal.getAnswer()))
        {
//            cardOptD.setCardBackgroundColor(getResources().getColor(R.color.design_default_color_secondary));
            if(index <= quizModals.size()-1)
            {
                correctans(cardOptD);
//                resetcolour();//some error

            }
            else
            {
                //GameWon();
            }
        }
        else
            wrongans(cardOptD);

    }
}