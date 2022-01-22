package com.example.guru_cares.customadapters;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guru_cares.R;
import com.example.guru_cares.modelclass.NoticeStudent_Model;

import java.util.ArrayList;

public class noticeStudentAdapter extends RecyclerView.Adapter<noticeStudentAdapter.noticeViewHolder> {

    Context context;
    ArrayList<NoticeStudent_Model> list;

    public noticeStudentAdapter(Context context, ArrayList<NoticeStudent_Model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public noticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.notice_layout_item,parent,false);
        return new noticeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull noticeViewHolder holder, int position) {

        NoticeStudent_Model notice=list.get(position);
        holder.noticeTitle.setText(notice.getNoticeTitle());
        holder.noticeData.setText(notice.getNoticeData());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class noticeViewHolder extends RecyclerView.ViewHolder{

        TextView noticeTitle, noticeData;

        public noticeViewHolder(@NonNull View itemView) {
            super(itemView);

            noticeTitle=itemView.findViewById(R.id.idTVNoticeTitle);
            noticeData=itemView.findViewById(R.id.idTVNoticeData);
        }

    }
}
