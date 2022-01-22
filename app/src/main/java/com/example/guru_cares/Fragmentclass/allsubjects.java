package com.example.guru_cares.Fragmentclass;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guru_cares.R;
import com.example.guru_cares.customadapters.chapter_custom_adapter;
import com.example.guru_cares.modelclass.chapter_model;
import com.example.guru_cares.modelclass.subject_model;
import com.example.guru_cares.customadapters.subjectname_custom_adapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link allsubjects#newInstance} factory method to
 * create an instance of this fragment.
 */


public class allsubjects extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public RecyclerView subjectname_recyclerview;
    public RecyclerView chaptername_recyclerview;
    public subjectname_custom_adapter subjectname_allsubjects_adapter;
    public chapter_custom_adapter chapter_allsubjects_adapter;

    private FirebaseDatabase db;

    public allsubjects() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment allsubjects.
     */
    // TODO: Rename and change types and number of parameters


    public static allsubjects newInstance(String param1, String param2) {
        allsubjects fragment = new allsubjects();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_allsubjects, container, false);

        Bundle bundle = this.getArguments();
        String subjectname_from_home = bundle.getString("subjectname");

       // Bundle bundles = this.getArguments();
        String studentcode =(String) bundle.getString("studentcode");
        String schoolname = (String) bundle.getString("schoolname");
        String sectioncode =(String) bundle.getString("sectioncode");
        String gradecode =(String) bundle.getString("gradecode");



        //Subject name horizontal recycler View
        subjectname_recyclerview = (RecyclerView) v.findViewById(R.id.subjectname_allsubjects);
        subjectname_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

        FirebaseRecyclerOptions<subject_model> options=
                new FirebaseRecyclerOptions.Builder<subject_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child(schoolname).child("100").child(gradecode).child("subjects"), subject_model.class)
                        .build();


        subjectname_allsubjects_adapter=new subjectname_custom_adapter(options,subjectname_from_home, schoolname, studentcode, gradecode, sectioncode);
        subjectname_recyclerview.setAdapter(subjectname_allsubjects_adapter);


        //Chapter vertical recycler Views
        chaptername_recyclerview = (RecyclerView) v.findViewById(R.id.chapters_recyclerview);
        chaptername_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<chapter_model> options_chapter=
                new FirebaseRecyclerOptions.Builder<chapter_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child(schoolname).child("100").child(gradecode).child("subjects").child(subjectname_from_home).child("chapters"), chapter_model.class)
                        .build();


        chapter_allsubjects_adapter=new chapter_custom_adapter(options_chapter, schoolname, gradecode, subjectname_from_home);
        chaptername_recyclerview.setAdapter(chapter_allsubjects_adapter);


        //Custom Card
        TextView subjectname = (TextView) v.findViewById(R.id.subjectname);
        TextView facultyname = (TextView) v.findViewById(R.id.facultyname);
        ImageView subjectpic = (ImageView) v.findViewById(R.id.subjectpic);
        CardView cardcolor = (CardView) v.findViewById(R.id.customcard_chapters);
        TextView sub_head = (TextView) v.findViewById(R.id.heading);




        db = FirebaseDatabase.getInstance();
        DatabaseReference datareference = db.getReference().child("schools").child(schoolname).child("100").child(gradecode).child("subjects").child(subjectname_from_home);


        //child("schools").child("trmps").child("students").child("grade1").child("sectionA").child("");

        datareference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                subject_model subjectinfo = snapshot.getValue(subject_model.class);
                subjectname.setText(subjectinfo.getSubjectname());
                facultyname.setText(subjectinfo.getFacultyname());
                cardcolor.setCardBackgroundColor(Color.parseColor("#" + subjectinfo.getCardcolor()));
                Glide.with(subjectpic.getContext()).load(subjectinfo.getImageurl()).into(subjectpic);
                sub_head.setText(subjectname_from_home);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Going to addsubject fragment to browse and upload pdf files
        ImageView addsubjectbtn = (ImageView) v.findViewById(R.id.addsubject);
        addsubjectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle_addchapter = new Bundle();
                bundle_addchapter.putString("schoolname", schoolname);
                bundle_addchapter.putString("studentcode", studentcode);
                bundle_addchapter.putString("gradecode", gradecode);
                bundle_addchapter.putString("sectioncode", sectioncode);
                bundle_addchapter.putString("subjectname", subjectname_from_home);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new addchapter();
                myFragment.setArguments(bundle_addchapter);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();

            }
        });











        return v;


    }



    @Override
    public void onStart() {
        super.onStart();
        chapter_allsubjects_adapter.startListening();
        subjectname_allsubjects_adapter.startListening();
    }




    @Override
    public void onStop() {
        super.onStop();
        chapter_allsubjects_adapter.stopListening();
        subjectname_allsubjects_adapter.stopListening();
    }
}