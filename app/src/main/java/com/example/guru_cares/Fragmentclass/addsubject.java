package com.example.guru_cares.Fragmentclass;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.guru_cares.R;
import com.example.guru_cares.modelclass.student_info_model;
import com.example.guru_cares.modelclass.subject_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addsubject#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addsubject extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addsubject() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addsubject.
     */
    // TODO: Rename and change types and number of parameters
    public static addsubject newInstance(String param1, String param2) {
        addsubject fragment = new addsubject();
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

        Bundle bundle_addsubject = this.getArguments();
        String imageurl = bundle_addsubject.getString("imageurl");
        String cardcolors = bundle_addsubject.getString("cardcolor");
        String userid = bundle_addsubject.getString("userid");
        String schoolname = bundle_addsubject.getString("schoolname");


        View v = inflater.inflate(R.layout.fragment_addsubject, container, false);



        CardView cardcolor = v.findViewById(R.id.customcard);
        ImageView subjectpic = v.findViewById(R.id.subjectpic);
        Glide.with(subjectpic.getContext()).load(imageurl).into(subjectpic);
        cardcolor.setCardBackgroundColor(Color.parseColor("#" + cardcolors));
        TextView addbtn = v.findViewById(R.id.addbtn);


        final String[] facultyname = new String[1];
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference ref = db.getReference().child("schools").child(schoolname).child("teachers").child(userid);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                student_info_model info = snapshot.getValue(student_info_model.class);

                String name = info.getName() + info.getBackname();
                facultyname[0] = name;


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        TextView customizenbtn = (TextView) v.findViewById(R.id.customizebtn);
        customizenbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundlefromaddsubject = new Bundle();
                bundlefromaddsubject.putString("userid", userid);
                bundlefromaddsubject.putString("schoolname", schoolname);

                Fragment myFragment = new allcustomcards();
                myFragment.setArguments(bundlefromaddsubject);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();


            }
        });
        EditText subjectname = v.findViewById(R.id.titlename);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String titlename = subjectname.getText().toString().trim();


                subject_model model = new subject_model(titlename, facultyname[0], cardcolors, imageurl, 0);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference().child("schools").child(schoolname).child("100").child("01").child("subjects");
                reference.child(titlename).setValue(model);


            }
        });




        return v;

    }
}