package com.example.guru_cares.Fragmentclass;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guru_cares.R;
import com.example.guru_cares.modelclass.QuizModal;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_task#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_task extends Fragment {
    //public static ArrayList<QuizModal> quizModals;
    //public static ArrayList<QuizModal> quizModals;

    public int quiz,assign= 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public add_task() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment add_task.
     */
    // TODO: Rename and change types and number of parameters
    public static add_task newInstance(String param1, String param2) {
        add_task fragment = new add_task();
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
        View v = inflater.inflate(R.layout.fragment_add_task, container, false);

        String schoolname;
        String gradecode;
        String facultyname;
        String username, userid, gradename, sectionname;


        Bundle bundle = this.getArguments();
        schoolname = bundle.getString("schoolname");
        facultyname = bundle.getString("facultyname");
        gradecode = bundle.getString("gradecode");
        username = bundle.getString("username");
        userid = bundle.getString("userid");
        gradename = bundle.getString("gradename");
        sectionname = bundle.getString("sectionname");



        LinearLayout addquiz = (LinearLayout) v.findViewById(R.id.addquiz);
        LinearLayout addassign = (LinearLayout) v.findViewById(R.id.addassignment);
        TextView addbtn = (TextView) v.findViewById(R.id.addbtn);

        addquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addquiz.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.taskoption));
                addassign.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.barbackgroundempty ));

                quiz=1;
                assign=0;

            }
        });


        addassign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addassign.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.taskoption));
                addquiz.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.barbackgroundempty) );

                assign=1;
                quiz=0;
            }
        });
                

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quiz==1)
                {
                    /*Intent intent = new Intent(getActivity(), DashboardActivity.class);
                    startActivity(intent);
                    quizModals = new ArrayList<>();
                    quizModals.add(new QuizModal("Samsung is a company of ?","South Korea","Brazil","USA","China","South Korea"));
                    quizModals.add(new QuizModal("Full form of API ?","Application Programming Implementation","Application Programming Interface","Automatic Programming Implementation","Application Processing Interface","Application Programming Interface"));
                    quizModals.add(new QuizModal("Types Of Layouts in Android Studio ?","Relative Layout","Linear Layout","Constraint Layout","All of the above","All of the above"));
                    quizModals.add(new QuizModal("Virtual Machine used for Android OS ?","JVM","Dalvik Virtual Machine","Simple Virtual Machine","None of the above","Dalvik Virtual Machine"));
                    quizModals.add(new QuizModal("Android is Licensed under ?","OSS","Sourceforge","Apache/MIT","None of the above","Sourceforge"));
*/

                    Bundle bundle_addtask = new Bundle();
                    bundle_addtask.putString("schoolname", schoolname);
                    bundle_addtask.putString("gradecode", gradecode);
                    bundle_addtask.putString("facultyname", facultyname);
                    bundle_addtask.putString("username", username);
                    bundle_addtask.putString("userid", userid);
                    bundle_addtask.putString("gradename", gradename);
                    bundle_addtask.putString("sectionname", sectionname);


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new quiz_info();
                    myFragment.setArguments(bundle_addtask);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();



                }

                else if(assign==1)
                {
                    Toast.makeText(getContext(), "All Assignments", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(),"Select atleast one option", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return v;

    }
}