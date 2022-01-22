package com.example.guru_cares.Fragmentclass;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guru_cares.R;
import com.example.guru_cares.modelclass.chapter_model;
import com.example.guru_cares.modelclass.subject_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link viewchapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class viewchapter extends Fragment {


    TextView subhead;
    TextView chaptername;
    TextView descript;
    TextView viewbtn;

    int n;

    String schoolname,gradecode,subjectname;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public viewchapter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment viewchapter.
     */
    // TODO: Rename and change types and number of parameters
    public static viewchapter newInstance(String param1, String param2) {
        viewchapter fragment = new viewchapter();
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
        View v = inflater.inflate(R.layout.fragment_viewchapter, container, false);

        Bundle b = this.getArguments();
        schoolname = b.getString("schoolname");
        gradecode = b.getString("gradecode");
        subjectname = b.getString("subjectname");
        n = b.getInt("index");


        subhead = (TextView) v.findViewById(R.id.subejctname);
        chaptername = (TextView) v.findViewById(R.id.chaptername);
        descript = (TextView) v.findViewById(R.id.chapterdescription);
        viewbtn = (TextView) v.findViewById(R.id.viewpdfbtn);

        subhead.setText(subjectname);

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("schools").child(schoolname).child("100").child(gradecode).child("subjects").child(subjectname).child("chapters").child("chapter"+n);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                chapter_model model = snapshot.getValue(chapter_model.class);
                assert model != null;
                String name = model.getChaptername();
                String des = model.getChapterdescription();
                String url = model.getFilelink();

                chaptername.setText(name);
                descript.setText(des);

                viewbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Uri uri = Uri.parse(url);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));

                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;

    }
}