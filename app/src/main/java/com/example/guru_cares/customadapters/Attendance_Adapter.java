package com.example.guru_cares.customadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.app.progresviews.ProgressWheel;
import com.example.guru_cares.R;

import java.util.ArrayList;

public class Attendance_Adapter extends RecyclerView.Adapter<Attendance_Adapter.MyView> {

    // List with String type
    private ArrayList<Mainviewmodel> list;
    Context context ;

    public Attendance_Adapter(Context context, ArrayList<Mainviewmodel> mainviewmodels) {
        this.context = context;
        this.list = mainviewmodels;
    }

    // View Holder class which
    // extends RecyclerView.ViewHolder
    public class MyView extends RecyclerView.ViewHolder {
        // Text View
        TextView subjectname;
        ProgressWheel progressWheel;

        // parameterised constructor for View Holder class
        // which takes the view as a parameter
        public MyView(View view) {
            super(view);

            // initialise TextView with id
            subjectname = (TextView) view.findViewById(R.id.attendance_recview_text);
            progressWheel = (ProgressWheel) view.findViewById(R.id.recview_item_wheelprogress);
        }
    }

    // Override onCreateViewHolder which deals
    // with the inflation of the card layout
    // as an item for the RecyclerView.
    @Override
    public MyView onCreateViewHolder(ViewGroup parent,
                                     int viewType)
    {

        // Inflate item.xml using LayoutInflator
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendance_recview_item,parent,false);

        // return itemView
        return new MyView(itemView);
    }

    // Override onBindViewHolder which deals
    // with the setting of different data
    // and methods related to clicks on
    // particular items of the RecyclerView.
    @Override
    public void onBindViewHolder(final MyView holder,
                                 final int position)
    {

        // Set the text of each item of
        // Recycler view with the list items
        holder.subjectname.setText(list.get(position).getSubjectName());
        holder.progressWheel.setPercentage((int)(list.get(position).getPercentage()*3.6));
        holder.progressWheel.setStepCountText(list.get(position).getPercentage().toString()+"%");
    }

    // Override getItemCount which Returns
    // the length of the RecyclerView.
    @Override
    public int getItemCount()
    {
        return list.size();
    }
}