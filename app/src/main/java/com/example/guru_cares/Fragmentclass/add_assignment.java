package com.example.guru_cares.Fragmentclass;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guru_cares.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_assignment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_assignment extends Fragment {

    EditText title;
    EditText desciption;
    TextView addassignmentbtn;

    ImageView removepdfbtn, pdfpic,browsepic;
    TextView browsebtn;

    String titlestr;
    String descriptionstr;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_assignment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_assignment.
     */
    // TODO: Rename and change types and number of parameters
    public static add_assignment newInstance(String param1, String param2) {
        add_assignment fragment = new add_assignment();
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
        View v = inflater.inflate(R.layout.fragment_add_assignment, container, false);

        removepdfbtn = (ImageView) v.findViewById(R.id.removepdfbtn);
        pdfpic = (ImageView) v.findViewById(R.id.pdfpic);
        browsepic = (ImageView) v.findViewById(R.id.browsepic);
        browsebtn = (TextView) v.findViewById(R.id.browsebtn);
        addassignmentbtn = (TextView) v.findViewById(R.id.addassignnmentbtn);
        title = (EditText) v.findViewById(R.id.assignmenttitlename);
        desciption = (EditText) v.findViewById(R.id.assignmentdescription);


        removepdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pdfpic.setVisibility(View.GONE);
                browsepic.setVisibility(View.VISIBLE);
                browsebtn.setVisibility(View.VISIBLE);
                removepdfbtn.setVisibility(View.GONE);
                //data.setData(null);
                addassignmentbtn.setVisibility(View.INVISIBLE);




            }
        });


        addassignmentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                titlestr = title.getText().toString();
                descriptionstr = desciption.getText().toString();






            }
        });




        return v;

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 12 && resultCode == getActivity().RESULT_OK && data!=null && data.getData()!=null)
        {
            //uploadbutton.setEnabled(true);
            //editText.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            addassignmentbtn.setVisibility(View.VISIBLE);
            browsepic.setVisibility(View.GONE);
            removepdfbtn.setVisibility(View.VISIBLE);
            pdfpic.setVisibility(View.VISIBLE);
            browsebtn.setVisibility(View.GONE);


            addassignmentbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //uploadbutton.setBackground(ContextCompat.getDrawable(getBaseContext(),R.drawable.darkblueoutline));
                    addassignmentbtn.setVisibility(View.INVISIBLE);
                    //uploadPDfFileFirebase(data.getData());
                }
            });
        }
        else
        {
            Toast.makeText(getContext(), "Select a pdf file to upload", Toast.LENGTH_SHORT).show();
        }






    }








}