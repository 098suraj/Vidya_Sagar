package com.example.guru_cares.Fragmentclass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guru_cares.R;
import com.example.guru_cares.customadapters.allcustomcards_custom_adapter;
import com.example.guru_cares.modelclass.subject_model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link allcustomcards#newInstance} factory method to
 * create an instance of this fragment.
 */
public class allcustomcards extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public allcustomcards() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment allcustomcards.
     */
    // TODO: Rename and change types and number of parameters
     String userid, schoolname;


    public allcustomcards newInstance(String param1, String param2, String userid, String schoolname) {
        allcustomcards fragment = new allcustomcards();
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

    public allcustomcards_custom_adapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_allcustomcards, container, false);
        RecyclerView rcv = v.findViewById(R.id.allcustomcardsrcv);
        GridLayoutManager gridlayoutmanager = new GridLayoutManager(getContext(), 2);
        rcv.setLayoutManager(gridlayoutmanager);

        FirebaseRecyclerOptions<subject_model> options=
                new FirebaseRecyclerOptions.Builder<subject_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("customcard"), subject_model.class)
                        .build();

        Bundle bundlefromaddsubject = this.getArguments();
        userid = bundlefromaddsubject.getString("userid");
        schoolname = bundlefromaddsubject.getString("schoolname");
        adapter =new allcustomcards_custom_adapter(options, userid, schoolname);
        rcv.setAdapter(adapter);












        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}