package com.example.guru_cares.customadapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;
import com.example.guru_cares.activityclass.gradelists;
import com.example.guru_cares.modelclass.grade_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class gradelist_customadapter extends FirebaseRecyclerAdapter<grade_model, gradelist_customadapter.gradelist_viewholder> {

    public String check = "false";
    public int pos = 99;
    public Context context;
    public sectionlist_customadapter sectionlist_customadapter;
    public String userid;
    public String username;
    public String gradename;
    public String sectionname;



    public gradelist_customadapter(@NonNull FirebaseRecyclerOptions<grade_model> options, Context context, String check, int pos, String userid , String username, String gradename, String sectionname) {
        super(options);
        this.check = check;
        this.context = context;
        this.pos = pos;
        this.userid = userid;
        this.username = username;
        this.gradename = gradename;
        this.sectionname = sectionname;



    }

    @Override
    protected void onBindViewHolder(@NonNull gradelist_viewholder holder, int position, @NonNull grade_model model) {

        holder.gradename.setText(model.getGradename());

        if(check.equals("true") && pos==position)
        {
            holder.gradecard.setBackgroundColor(Color.parseColor("#FFE979"));
            holder.movablelist.setVisibility(View.VISIBLE);
            holder.arrow.setImageResource(R.drawable.up);
        }
        else
        {

        }


        holder.gradecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(username ==  null)
                {
                    Toast.makeText(context,"Null on grade adapter", Toast.LENGTH_SHORT).show();
                }

                if(check.equals("true") && pos==position)
                {
                    check="false";
                    holder.gradecard.setBackgroundColor(Color.parseColor("#ffffff"));
                    holder.movablelist.setVisibility(View.GONE);
                    holder.arrow.setImageResource(R.drawable.down);

                }
                else
                {


                    check = "true";
                    pos = position;

                   /* Bundle bundle = new Bundle();
                    bundle.putString("check", check);
                    bundle.putInt("position", position);
                    bundle.putString("gradename", model.getGradename());*/

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    /*Fragment myFragment = new gradelist();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();*/

                    gradename = model.getGradename();
                    Intent i = new Intent(context, gradelists.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("check", check);
                    i.putExtra("position", position);
                    i.putExtra("gradename", gradename);
                    i.putExtra("sectionname", sectionname);
                    i.putExtra("username", username);
                    i.putExtra("userid", userid);

                    activity.startActivity(i);




                }


            }
        });


        ArrayList<String> sectionslist = new ArrayList<>();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference reference = db.getReference().child("schools").child("trmps").child("grades").child(model.gradename).child("sections");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String info = snapshot.getValue(String.class);

                // holder.gradename.setText(info);
                int size = 2;

                if(info.equals("three"))
                {
                    size = 3;
                }
                else if(info.equals("four"))
                {
                    size = 4;
                }
                else if(info.equals("five"))
                {
                    size = 5;
                }
                else if(info.equals("six"))
                {
                    size = 6;
                }

                char letter = 'A';
                for(int i=0;i<size;i++)
                {
                    sectionslist.add("Section - "+ letter);

                    letter++;



                }

                holder.sectionrecycler.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
                sectionlist_customadapter = new sectionlist_customadapter(sectionslist, context, check, position, model.getGradename(), userid , username);
                holder.sectionrecycler.setAdapter(sectionlist_customadapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        /*ArrayList<String> sectionslist = new ArrayList<>();
        sectionslist.add("Section - A");
        sectionslist.add("Section - B");
        sectionslist.add("Section - C");
        sectionslist.add("Section - D");*/






    }

    @NonNull
    @Override
    public gradelist_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_grade_layout,parent,false);
        return new gradelist_viewholder(view);
    }

    static class gradelist_viewholder extends RecyclerView.ViewHolder
    {
        TextView gradename;
        LinearLayout gradecard;
        LinearLayout movablelist;
        ImageView arrow;
        RecyclerView sectionrecycler;

        public gradelist_viewholder(@NonNull View itemView) {
            super(itemView);
            gradename = itemView.findViewById(R.id.gradename);
            gradecard = itemView.findViewById(R.id.gradecard);
            movablelist = itemView.findViewById(R.id.movablesectionlist);
            arrow = (ImageView) itemView.findViewById(R.id.arrow);
            sectionrecycler = itemView.findViewById(R.id.sectionrecycler);


        }


    }



}