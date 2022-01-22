package com.example.guru_cares.customadapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;
import com.example.guru_cares.Fragmentclass.allsubjects;
import com.example.guru_cares.modelclass.subject_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class subjectname_custom_adapter extends FirebaseRecyclerAdapter<subject_model,subjectname_custom_adapter.myviewholder> {


   // private String previous;
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public static String previous = "";
    private String schoolname, studentcode , gradecode, sectioncode;
    public subjectname_custom_adapter(@NonNull FirebaseRecyclerOptions<subject_model> options, String color, String schoolname , String studentcode , String gradecode, String sectioncode) {
        super(options);
        previous = color;
        this.schoolname = schoolname;
        this.studentcode = studentcode;
        this.gradecode = gradecode;
        this.sectioncode = sectioncode;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull subject_model model) {
        holder.subjectname.setText(model.getSubjectname());
       // holder.subjectname.setTextColor(R.color.purple_200);



        if(model.getSubjectname().equals(previous))
        {

            holder.subjectname.setTextColor(Color.parseColor("#000000"));
            holder.subjectname.setBackgroundColor(Color.parseColor("#" + model.getCardcolor()));

        }





        holder.subjectname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                previous = model.getSubjectname().toString();
                Bundle bundle = new Bundle();
                bundle.putString("subjectname", model.getSubjectname());
                bundle.putString("studentcode", studentcode);
                bundle.putString("schoolname", schoolname);
                bundle.putString("gradecode", gradecode);
                bundle.putString("sectioncode", sectioncode);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new allsubjects();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();



            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subjectname_custom_layout,parent,false);
        return new myviewholder(view);
    }

    public static class myviewholder extends RecyclerView.ViewHolder
    {

        TextView subjectname;



        public myviewholder(@NonNull View itemView) {
            super(itemView);
            subjectname = (TextView)itemView.findViewById(R.id.subjectname);



        }
    }



}
