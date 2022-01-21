package com.example.guru_cares.customadapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;
import com.example.guru_cares.activityclass.MainActivity;

import java.util.ArrayList;

public class sectionlist_customadapter extends RecyclerView.Adapter<sectionlist_customadapter.sectionlist_viewholder> {


    public String sectionname = "Section";
    public String gradename;
    public String check;
    public int pos;
    public String check_section = "false";
    public Context context;
    public ArrayList<String> sectionslist = new ArrayList<>();
    public String userid;
    public String username;



    public sectionlist_customadapter(ArrayList<String> sectionslist, Context context, String check, int position, String gradename, String userid , String use) {
        this.sectionslist = sectionslist;
        this.context = context;
        this.pos = position;
        this.check = check;
        this.gradename = gradename;
        this.userid = userid;
        this.username= use;
        //this.username = "2000101@trmpssajal.edu";


    }



    @NonNull
    @Override
    public sectionlist_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v = inflator.inflate(R.layout.custom_section_layout, parent, false);
        return new sectionlist_customadapter.sectionlist_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull sectionlist_viewholder holder, int position) {

        holder.sectionname.setText(sectionslist.get(position));
        //holder.sectionname.setText(username);
        holder.sectionname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.sectionname.setBackgroundColor(Color.parseColor("#FFE979"));


                sectionname = sectionslist.get(position);

                /*Bundle bundle = new Bundle();
                bundle.putString("sectionname", sectionslist.get(position));
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new gradelist();
                myFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();*/


                if(username==null)
                {
                    Toast.makeText(context,"null", Toast.LENGTH_SHORT).show();
                }

                AppCompatActivity activity = (AppCompatActivity) v.getContext();

                Intent i = new Intent(context, MainActivity.class);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //i.putExtra("check", check);
                //i.putExtra("position", position);
                i.putExtra("gradename",gradename);
                i.putExtra("sectionname", sectionslist.get(position));
                i.putExtra("userid", userid);
                i.putExtra("username", username);
                //activity.startActivity(i);




            }
        });


    }

    @Override
    public int getItemCount() {
        return sectionslist.size();
    }


    static class sectionlist_viewholder extends RecyclerView.ViewHolder {
        TextView sectionname;

        public sectionlist_viewholder(@NonNull View itemView) {
            super(itemView);
            sectionname = itemView.findViewById(R.id.sectionname);





        }


    }


}