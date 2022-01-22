package com.example.guru_cares.customadapters;

import android.content.Intent;
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
import com.example.guru_cares.activityclass.DashboardActivity;
import com.example.guru_cares.Fragmentclass.classroom;
import com.example.guru_cares.Fragmentclass.viewchapter;
import com.example.guru_cares.modelclass.chapter_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class chapter_custom_adapter extends FirebaseRecyclerAdapter<chapter_model,chapter_custom_adapter.myviewholder> {

    String schoolname, gradecode, subjectname;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public chapter_custom_adapter(@NonNull FirebaseRecyclerOptions<chapter_model> options, String schoolname, String gradecode, String subjectname) {
        super(options);
        this.schoolname= schoolname;
        this.gradecode = gradecode;
        this.subjectname = subjectname;

    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull chapter_model model) {

        holder.chaptername.setText(model.getChaptername());

        holder.chaptername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b = new Bundle();
                b.putString("schoolname", schoolname);
                b.putString("gradecode", gradecode);
                b.putString("subjectname", subjectname);
                b.putInt("index", position+1);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new viewchapter();
                myFragment.setArguments(b);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();


            }
        });
        //Glide.with(holder.chapterpic.getContext()).load(model.getImageurl()).into(holder.chapterpic);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_custom_layout,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {
        TextView chaptername;
        //TextView chapterdescription;
       // TextView viewbtn;

       // ImageView chapterpic;


        public myviewholder(@NonNull View itemView) {
            super(itemView);
            chaptername = (TextView) itemView.findViewById(R.id.chaptername);
           // chapterdescription = (TextView) itemView.findViewById(R.id.chapterdescription);
           // viewbtn = (TextView) itemView.findViewById(R.id.viewpdfbtn);

            //chapterpic = (ImageView) itemView.findViewById(R.id.chapterpic);

        }


    }
}
