package com.example.guru_cares.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;

public class home_viewholder extends RecyclerView.ViewHolder {

    public ImageView subjectimage;
    public TextView subjectname;
    public TextView facultyname;
    public TextView enrollbtn;




    public home_viewholder(@NonNull View itemView) {
        super(itemView);

        subjectimage = (ImageView)itemView.findViewById(R.id.subjectpic);
        subjectname = (TextView)itemView.findViewById(R.id.subjectname);
        facultyname = (TextView)itemView.findViewById(R.id.facultyname);
        enrollbtn = (TextView) itemView.findViewById(R.id.enroll);




    }
}
