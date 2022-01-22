package com.example.guru_cares.customadapters;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guru_cares.R;
import com.example.guru_cares.Fragmentclass.allsubjects;
import com.example.guru_cares.modelclass.subject_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class home_firebasecustomadapter extends FirebaseRecyclerAdapter<subject_model,home_firebasecustomadapter.myviewholder> {

    public String schoolname, sectioncode ,gradecode, studentcode;

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */


    public home_firebasecustomadapter(@NonNull FirebaseRecyclerOptions<subject_model> options, String schoolname , String gradecode , String sectioncode ,String studentcode) {
        super(options);
        this.schoolname=schoolname;
        this.sectioncode = sectioncode;
        this.gradecode = gradecode;
        this.studentcode = studentcode;



    }


    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull subject_model model) {

        holder.subjectname.setText(model.getSubjectname());
        holder.facultyname.setText(model.getFacultyname());
        Glide.with(holder.imageurl.getContext()).load(model.getImageurl()).into(holder.imageurl);
        //String hexcolor = model.getCardcolor();
        holder.cardcolor.setCardBackgroundColor(Color.parseColor("#" + model.getCardcolor()));



        holder.cardcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Making a bundle and sending the subject name from home fragment custom card to the chapter fragment
                Bundle bundle = new Bundle();
                bundle.putString("subjectname", model.getSubjectname());

                //Bundle bundles = new Bundle();
                bundle.putString("studentcode", studentcode);
                bundle.putString("schoolname", schoolname);
                bundle.putString("gradecode", gradecode);
                bundle.putString("sectioncode", sectioncode);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new allsubjects();
                myFragment.setArguments(bundle);
                //myFragment.setArguments(bundles);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();





            }
        });



        /*if(position==0)
        {
            holder.cardimage.setImageResource(R.drawable.ai);
            holder.cardcolor.setCardBackgroundColor(Color.parseColor("#85D1FF"));
        }
        else if(position==2)
        {
            holder.cardimage.setImageResource(R.drawable.math);
            holder.cardcolor.setCardBackgroundColor(Color.parseColor("#FFE979"));
        }
        else if(position==3)
        {
            holder.cardimage.setImageResource(R.drawable.atom);
            holder.cardcolor.setCardBackgroundColor(Color.parseColor("#ECC6FF"));
        }*/

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_custom_layout,parent,false);
        if(studentcode.equals("100"))
        {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_custom_layout,parent,false);

        }
        else if(studentcode.equals("200"))
        {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminhome_custom_layout,parent,false);


        }
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView subjectname, facultyname;
        ImageView imageurl;

        CardView cardcolor;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            subjectname = (TextView)itemView.findViewById(R.id.subjectname);
            facultyname=(TextView)itemView.findViewById(R.id.facultyname);
            imageurl = (ImageView)itemView.findViewById(R.id.subjectpic);
            cardcolor = (CardView)itemView.findViewById(R.id.customcard);
        }
    }
}
