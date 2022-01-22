package com.example.guru_cares;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guru_cares.activityclass.mcard;
import com.example.guru_cares.timeTable.MainActivityCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dash#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dash extends Fragment {
    CardView timetable;
    CardView Bhagwadgeeta;
    CardView mentorcard;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dash() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dash.
     */
    // TODO: Rename and change types and number of parameters
    public static Dash newInstance(String param1, String param2) {
        Dash fragment = new Dash();
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
        View view=inflater.inflate(R.layout.fragment_dash, container, false);
        timetable=view.findViewById(R.id.timetable);
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivityCalendar.class);
                startActivity(intent);
            }
        });

        Bhagwadgeeta = view.findViewById(R.id.geeta);


        mentorcard = view.findViewById(R.id.mentorcardbtn);
        mentorcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), mcard.class);
                startActivity(i);

            }
        });


        return view;
    }

}