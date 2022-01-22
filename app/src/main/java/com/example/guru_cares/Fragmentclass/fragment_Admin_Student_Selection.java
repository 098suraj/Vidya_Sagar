package com.example.guru_cares.Fragmentclass;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.guru_cares.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_Admin_Student_Selection#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_Admin_Student_Selection extends Fragment {
    ImageView adminAccess,studentAccess;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_Admin_Student_Selection() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_Admin_Student_Selection.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_Admin_Student_Selection newInstance(String param1, String param2) {
        fragment_Admin_Student_Selection fragment = new fragment_Admin_Student_Selection();
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

        View view=inflater.inflate(R.layout.fragment__admin__student__selection, container, false);
        adminAccess= view.findViewById(R.id.adminAccess);
        studentAccess=view.findViewById(R.id.studentAccess);
        adminAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new AdminFragment();
                //myFragment.setArguments(bundle_addsubject);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();

            }
        });

        studentAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new StudentFragment();
                //myFragment.setArguments(bundle_addsubject);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();

            }
        });



        return view;
    }
}