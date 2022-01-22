package com.example.guru_cares.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;
import com.example.guru_cares.viewholder.home_viewholder;
import com.example.guru_cares.modelclass.model;

import java.util.ArrayList;

public class home_customadapter extends RecyclerView.Adapter<home_viewholder> {


    public ArrayList<model> data;
    public Context context;


    public home_customadapter(ArrayList<model> data, Context context) {
        this.data = data;
        this.context = context;

    }


    @NonNull
    @Override
    public home_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(parent.getContext());
        View v = inflator.inflate(R.layout.home_custom_layout, parent, false);
        return new home_viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull home_viewholder holder, int position) {

        holder.subjectimage.setImageResource(data.get(position).getSubjectimage());
        holder.subjectname.setText(data.get(position).getSubjectname());
        holder.facultyname.setText(data.get(position).getFacultyname());
        holder.enrollbtn.setText(data.get(position).getEnrollbtn());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
