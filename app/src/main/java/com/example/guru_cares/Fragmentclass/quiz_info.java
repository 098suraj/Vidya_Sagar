package com.example.guru_cares.Fragmentclass;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guru_cares.R;
import com.example.guru_cares.activityclass.Questions_n_Answers;
import com.example.guru_cares.modelclass.QuizModal;
import com.example.guru_cares.modelclass.quiz_info_model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link quiz_info#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class quiz_info extends Fragment {
    public static ArrayList<QuizModal> quizModals;
    TextView nextbtn;
    EditText quizsubject, nquestions, timeduration;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String schoolname;
    String gradecode;
    String facultyname;
    String username, userid, gradename, sectionname;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment quiz_info.
     */
    // TODO: Rename and change types and number of parameters
    public static quiz_info newInstance(String param1, String param2) {
        quiz_info fragment = new quiz_info();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public quiz_info() {
        // Required empty public constructor
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
        View v = inflater.inflate(R.layout.fragment_quiz_info, container, false);




        Bundle bundle = this.getArguments();
        schoolname = bundle.getString("schoolname");
        facultyname = bundle.getString("facultyname");
        gradecode = bundle.getString("gradecode");
        username = bundle.getString("username");
        userid = bundle.getString("userid");
        gradename = bundle.getString("gradename");
        sectionname = bundle.getString("sectionname");


        quizModals = new ArrayList<QuizModal>();

        nextbtn = (TextView) v.findViewById(R.id.nextbtn);
        quizsubject = (EditText) v.findViewById(R.id.quiztitle);
        nquestions = (EditText) v.findViewById(R.id.nquestions);
        timeduration = (EditText) v.findViewById(R.id.timeduration);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                quiz_info_model model = new quiz_info_model(quizsubject.getText().toString(), "quiz", facultyname, Integer.parseInt(nquestions.getText().toString()), Integer.parseInt(timeduration.getText().toString()));

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference reference = database.getReference().child("schools").child(schoolname).child("100").child(gradecode).child("tasks");
                reference.child(quizsubject.getText().toString()).setValue(model);


                Intent i = new Intent (getContext(), Questions_n_Answers.class);
                i.putExtra("nquestions", nquestions.getText().toString());
                i.putExtra("quizsubject", quizsubject.getText().toString());
                i.putExtra("timeduration", timeduration.getText().toString());
                i.putExtra("schoolname", schoolname);
                i.putExtra("gradecode", gradecode);
                i.putExtra("facultyname", facultyname);
                i.putExtra("username", username);
                i.putExtra("userid", userid);
                i.putExtra("gradename", gradename);
                i.putExtra("sectionname", sectionname);

                startActivity(i);

            }
        });


        return v;
    }
}