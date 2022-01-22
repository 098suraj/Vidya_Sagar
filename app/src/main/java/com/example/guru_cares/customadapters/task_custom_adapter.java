package com.example.guru_cares.customadapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;
import com.example.guru_cares.activityclass.DashboardActivity;
import com.example.guru_cares.modelclass.QuizModal;
import com.example.guru_cares.modelclass.task_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.guru_cares.Fragmentclass.quiz_info.quizModals;


public class task_custom_adapter extends FirebaseRecyclerAdapter<task_model, task_custom_adapter.myviewholder> {


    String schoolname, gradecode;
    Context context;
    int nquestions;
    String username, userid, gradename, sectionname;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public task_custom_adapter(@NonNull FirebaseRecyclerOptions<task_model> options, Context context, String username, String userid, String gradename, String sectionname) {
        super(options);
        this.context = context;

        this.username = username;
        this.userid = userid;
        this.gradename = gradename;
        this.sectionname = sectionname;

    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull task_model model) {

        holder.quizsubject.setText(model.getQuizsubject());


        //Traversing every snapshot of tasks and identifying its type
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference().child("schools").child("trmps").child("100").child("01").child("tasks").child(model.getQuizsubject());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                task_model model = snapshot.getValue(task_model.class);
                assert model != null;
                String type = model.getType();

                if(type.equals("quiz"))
                {
                    nquestions = model.getNquestions();
                    //Toast.makeText(context, nquestions + type, Toast.LENGTH_SHORT).show();
                }


                if(type.toString().equals("assignment"))
                {
                    holder.customcard.setCardBackgroundColor(Color.parseColor("#FFE8F2"));
                    holder.subjectpic.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.notes));
                }
                else
                {

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        holder.enrollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(model.getType().equals("quiz"))
                {

                    quizModals = new ArrayList<>();

                    //reference = db.getReference().child("schools").child("trmps").child("100").child("01").child("tasks").child(model.getQuizsubject()).child("questions").child("question1");
                    for(int i=1;i<=model.getNquestions();i++)
                    {
                        //reference.child("type");
                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference reference = db.getReference().child("schools").child("trmps").child("100").child("01").child("tasks").child(model.getQuizsubject()).child("questions").child("question"+i);
                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                QuizModal quizmodel = snapshot.getValue(QuizModal.class);
                                assert quizmodel != null;
                                String question = quizmodel.getQuestion();
                                String option1 = quizmodel.getOption1();
                                String option2 = quizmodel.getOption2();
                                String option3 = quizmodel.getOption3();
                                String option4 = quizmodel.getOption4();
                                String answer = quizmodel.getAnswer();

                                //Toast.makeText(context, question, Toast.LENGTH_SHORT).show();

                                quizModals.add(new QuizModal(question, option1, option2, option3, option4, answer));

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                    Intent i = new Intent(context, DashboardActivity.class);
                    i.putExtra("username", username);
                    i.putExtra("userid", userid);
                    i.putExtra("gradename", gradename);
                    i.putExtra("sectionname", sectionname);
                    context.startActivity(i);





                }
                else
                {
                    Toast.makeText(context, "assignment on developement", Toast.LENGTH_SHORT).show();
                }






            }
        });




    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_custom_layout,parent,false);
        return new myviewholder(view);
    }



    //inner static class of view holder
    static class myviewholder extends RecyclerView.ViewHolder
    {

        TextView quizsubject;
        CardView customcard;
        ImageView subjectpic;
        TextView enrollbtn;
        TextView facultyname;



        public myviewholder(@NonNull View itemView) {
            super(itemView);
            quizsubject = (TextView) itemView.findViewById(R.id.quizsubjectname);
            customcard = (CardView) itemView.findViewById(R.id.customcard);
            subjectpic = (ImageView) itemView.findViewById(R.id.subjectpic);
            enrollbtn = (TextView) itemView.findViewById(R.id.enroll);
            facultyname = (TextView) itemView.findViewById(R.id.facultyname);




        }
    }
}
