package com.example.guru_cares;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guru_cares.modelclass.student_info_model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link User#newInstance} factory method to
 * create an instance of this fragment.
 */
public class User extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public User() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment User.
     */
    // TODO: Rename and change types and number of parameters
    public static User newInstance(String param1, String param2) {
        User fragment = new User();
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
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_user, container, false);


        Bundle bundle = this.getArguments();
        assert bundle != null;
        String username = bundle.getString("username");
        String userid = bundle.getString("userid");
        String schoolname = (String) username.substring(8,13).toString();
        String studentcode = username.substring(0,3);
        String gradecode = username.substring(3,5);
        String sectioncode = username.substring(5,7);


        TextView frontname = v.findViewById(R.id.frontname);
        TextView backname = v.findViewById(R.id.backname);
        TextView phone = v.findViewById(R.id.phone);
        TextView address = v.findViewById(R.id.address);
        TextView usernameview =  v.findViewById(R.id.username);


        if(studentcode.equals("100"))
        {

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference reference = db.getReference().child("schools").child(schoolname).child(studentcode).child(gradecode).child(sectioncode).child(userid);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    student_info_model info = snapshot.getValue(student_info_model.class);
                    frontname.setText(info.getName());
                    backname.setText(info.getBackname());
                    phone.setText(info.getPhone());
                    address.setText(info.getAddress());
                    usernameview.setText(username);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });




        }
        else if(studentcode.equals("200"))
        {

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference reference = db.getReference().child("schools").child(schoolname).child("teachers").child(userid);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    student_info_model info = snapshot.getValue(student_info_model.class);
                    frontname.setText(info.getName());
                    backname.setText(info.getBackname());
                    phone.setText(info.getPhone());
                    address.setText(info.getAddress());
                    usernameview.setText(username);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



        }













        return v;
    }
}