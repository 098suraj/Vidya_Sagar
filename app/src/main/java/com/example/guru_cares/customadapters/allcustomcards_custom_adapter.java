package com.example.guru_cares.customadapters;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.guru_cares.R;
import com.example.guru_cares.Fragmentclass.addsubject;
import com.example.guru_cares.modelclass.subject_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class allcustomcards_custom_adapter extends FirebaseRecyclerAdapter<subject_model, allcustomcards_custom_adapter.myviewholder> {



    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    final String userid , schoolname;
    public allcustomcards_custom_adapter(@NonNull FirebaseRecyclerOptions<subject_model> options, String userid , String schoolname ) {
        super(options);
        this.userid = userid;
        this.schoolname = schoolname ;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull subject_model model) {


        Glide.with(holder.imageurl.getContext()).load(model.getImageurl()).into(holder.imageurl);
        holder.cardcolor.setCardBackgroundColor(Color.parseColor("#" + model.getCardcolor()));

        holder.cardcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle_addsubject = new Bundle();
                bundle_addsubject.putString("imageurl", model.getImageurl());
                bundle_addsubject.putString("cardcolor", model.getCardcolor());
                bundle_addsubject.putString("userid", userid);
                bundle_addsubject.putString("schoolname", schoolname);


                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new addsubject();
                myFragment.setArguments(bundle_addsubject);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();


            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allcustomcards_custom_layout,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder
    {

        ImageView imageurl;
        CardView cardcolor;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imageurl = (ImageView)itemView.findViewById(R.id.subjectpic);
            cardcolor = (CardView)itemView.findViewById(R.id.customcard);
        }
    }




}
