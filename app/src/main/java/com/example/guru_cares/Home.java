package com.example.guru_cares;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.guru_cares.Fragmentclass.Dmodelview;
import com.example.guru_cares.Fragmentclass.add_task;
import com.example.guru_cares.Fragmentclass.addsubject;
import com.example.guru_cares.Fragmentclass.classroom;
import com.example.guru_cares.customadapters.home_firebasecustomadapter;
import com.example.guru_cares.customadapters.task_custom_adapter;
import com.example.guru_cares.modelclass.model;
import com.example.guru_cares.modelclass.student_info_model;
import com.example.guru_cares.modelclass.subject_model;
import com.example.guru_cares.modelclass.task_model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public String facultyname ;
    public RecyclerView subject_recyclerview;
    public RecyclerView task_recyclerview;
    // public home_customadapter task_adapter;
    //public home_customadapter ad;
    public home_firebasecustomadapter firebase_subject_adapter;
    public task_custom_adapter task_adapter;


    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //Fetching name and grade from firebase as per its username and userid

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView grade = (TextView) v.findViewById(R.id.grade);
        Bundle bundle = this.getArguments();
        assert bundle != null;
        String username = bundle.getString("username");
        String userid = bundle.getString("userid");
        String gradename = bundle.getString("gradename");
        String sectionname = bundle.getString("sectionname");
        String schoolname = (String) username.substring(8,13).toString();
        String studentcode = username.substring(0,3);
        String gradecode = username.substring(3,5).toString();
        String sectioncode = username.substring(5,7);
        ImageView toogle=v.findViewById(R.id.toogle);

        ImageView addtask = v.findViewById(R.id.addtask);
        ImageView addsubject = v.findViewById(R.id.addsubject);
        TextView header = v.findViewById(R.id.header);
        TextView headerbutton  = v.findViewById(R.id.headerbutton);

        DrawerLayout dr=getActivity().findViewById(R.id.drawerlayout);
        toogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dr.openDrawer(GravityCompat.START);
            }
        });
        LinearLayout viewmodel = (LinearLayout) v.findViewById(R.id.dviewmodel);
        viewmodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fragment myFragment = new Dmodelview();
                //myFragment.setArguments(bundle_addsubject);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();


            }
        });


        if(studentcode.equals("100"))
        {

            addsubject.setVisibility(View.GONE);
            addtask.setVisibility(View.GONE);

            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference reference = db.getReference().child("schools").child(schoolname).child(studentcode).child(gradecode).child(sectioncode).child(userid);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    student_info_model info = snapshot.getValue(student_info_model.class);
                    name.setText(info.getName());
                    grade.setText(info.getGrade());




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            //Sending codes to allchapters fragment via bundle
       /* Bundle bundles = new Bundle();
        bundles.putString("schoolname", schoolname);
        bundles.putString("gradecode", gradecode);
        bundles.putString("studentcode", studentcode);
        bundles.putString("sectioncode", sectioncode);
        Fragment allsubjects = new allsubjects();
        allsubjects.setArguments(bundles);*/








            //Linking Task Recycler view with Recylerview object and setting up its layout manager and adapter
           /* task_recyclerview = (RecyclerView) v.findViewById(R.id.task_recycle);
            task_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
            task_adapter = new home_customadapter(dataque_task(), getContext());
            task_recyclerview.setAdapter(task_adapter);*/

            task_recyclerview = (RecyclerView) v.findViewById(R.id.task_recycle);
            task_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

            FirebaseRecyclerOptions<task_model> task_options=
                    new FirebaseRecyclerOptions.Builder<task_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child(schoolname).child("100").child(gradecode).child("tasks"), task_model.class)
                            .build();

            task_adapter =new task_custom_adapter(task_options, getContext(), username , userid , gradename, sectionname );
            task_recyclerview.setAdapter(task_adapter);





            //Linking Subject Recycler with firebase adapter and setting out its layout manager
            subject_recyclerview = (RecyclerView) v.findViewById(R.id.subject_recycle);
            subject_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

            //Used specifiaclly for firebase custom adapters of recycle views
            FirebaseRecyclerOptions<subject_model> options=
                    new FirebaseRecyclerOptions.Builder<subject_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child(schoolname).child(studentcode).child(gradecode).child("subjects"), subject_model.class)
                            .build();


            firebase_subject_adapter=new home_firebasecustomadapter(options, schoolname, gradecode , sectioncode, studentcode);
            subject_recyclerview.setAdapter(firebase_subject_adapter);








        }

        else if(studentcode.equals("200"))
        {
            //final String[] facultyname = new String[1];
            String headertext = "Check Your Class";
            String headerbuttontext = "Open Class";

            header.setText(headertext);
            headerbutton.setText(headerbuttontext);


            headerbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new classroom();
                    //myFragment.setArguments(bundle_addsubject);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();




                }
            });





            addsubject.setVisibility(View.VISIBLE);
            addsubject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle_addsubject = new Bundle();
                    bundle_addsubject.putString("imageurl","https://firebasestorage.googleapis.com/v0/b/realtimefirebase-1588c.appspot.com/o/math.png?alt=media&token=9526f233-caa9-4b33-a891-991ddc5edbf5");
                    bundle_addsubject.putString("cardcolor","FFE979");
                    bundle_addsubject.putString("userid", userid);
                    bundle_addsubject.putString("schoolname", schoolname);


                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new addsubject();
                    myFragment.setArguments(bundle_addsubject);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();



                }
            });

            gradecode ="0" + gradename.substring(8,9);

            String section = sectionname.substring(10,11);
            if(section.equals("A"))
            {
                sectioncode = "01";
            }
            else if(section.equals("B"))
            {
                sectioncode = "02";
            }
            else if(section.equals("C"))
            {
                sectioncode = "03";
            }
            else if(section.equals("D"))
            {
                sectioncode = "03";
            }
            else if(section.equals("E"))
            {
                sectioncode = "04";
            }



            // Toast.makeText(getContext(),schoolname + "  " + gradecode + "  " + sectioncode , Toast.LENGTH_SHORT).show();
            FirebaseDatabase db = FirebaseDatabase.getInstance();
            DatabaseReference reference = db.getReference().child("schools").child(schoolname).child("teachers").child(userid);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    student_info_model info = snapshot.getValue(student_info_model.class);
                    name.setText(info.getName());
                    String gradetxt = gradename +" , " + sectionname;
                    grade.setText(gradetxt);




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            //Sending codes to allchapters fragment via bundle
       /* Bundle bundles = new Bundle();
        bundles.putString("schoolname", schoolname);
        bundles.putString("gradecode", gradecode);
        bundles.putString("studentcode", studentcode);
        bundles.putString("sectioncode", sectioncode);
        Fragment allsubjects = new allsubjects();
        allsubjects.setArguments(bundles);*/








            //Linking Task Recycler view with Recylerview object and setting up its layout manager and adapter
            /*task_recyclerview = (RecyclerView) v.findViewById(R.id.task_recycle);
            task_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));
            task_adapter = new home_customadapter(dataque_task(), getContext());
            task_recyclerview.setAdapter(task_adapter);*/

            task_recyclerview = (RecyclerView) v.findViewById(R.id.task_recycle);
            task_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));

            FirebaseRecyclerOptions<task_model> task_options=
                    new FirebaseRecyclerOptions.Builder<task_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child(schoolname).child("100").child(gradecode).child("tasks"), task_model.class)
                            .build();

            task_adapter =new task_custom_adapter(task_options, getContext(), username, userid, gradename, sectionname);
            task_recyclerview.setAdapter(task_adapter);




            //Linking Subject Recycler with firebase adapter and setting out its layout manager
            subject_recyclerview = (RecyclerView) v.findViewById(R.id.subject_recycle);
            subject_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.HORIZONTAL,false));

            //ad = new home_customadapter(dataque(), getContext());
            //rcv.setAdapter(ad);

            //Used specifiaclly for firebase custom adapters of recycle views
            FirebaseRecyclerOptions<subject_model> options=
                    new FirebaseRecyclerOptions.Builder<subject_model>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child(schoolname).child("100").child(gradecode).child("subjects"), subject_model.class)
                            .build();


            firebase_subject_adapter=new home_firebasecustomadapter(options, schoolname, gradecode , sectioncode, studentcode);
            subject_recyclerview.setAdapter(firebase_subject_adapter);



            FirebaseDatabase dbs = FirebaseDatabase.getInstance();
            DatabaseReference ref = dbs.getReference().child("schools").child(schoolname).child("teachers").child(userid);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    student_info_model info = snapshot.getValue(student_info_model.class);

                    //Toast.makeText(getContext(), info.getName(), Toast.LENGTH_SHORT).show();

                    facultyname = info.getName() + info.getBackname();
                    //facultyname= name;


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });



            addtask.setVisibility(View.VISIBLE);
            String finalGradecode = gradecode;
            addtask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    //final String[] facultyname = new String[1];







                    Bundle bundle_addtask = new Bundle();
                    bundle_addtask.putString("schoolname", schoolname);
                    bundle_addtask.putString("gradecode", finalGradecode);
                    bundle_addtask.putString("facultyname", facultyname);
                    bundle_addtask.putString("username", username);
                    bundle_addtask.putString("userid", userid);
                    bundle_addtask.putString("gradename", gradename);
                    bundle_addtask.putString("sectionname", sectionname);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new add_task();
                    myFragment.setArguments(bundle_addtask);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();


                    // Toast.makeText(getContext(), schoolname + facultyname+ finalGradecode, Toast.LENGTH_SHORT).show();


                }
            });





        }









        return v;



    }

    @Override
    public void onStart() {
        super.onStart();
        task_adapter.startListening();
        firebase_subject_adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        task_adapter.stopListening();
        firebase_subject_adapter.stopListening();
    }


    public ArrayList<model> dataque_task()
    {

        ArrayList<model> holder = new ArrayList<>();

        holder.add(new model(R.drawable.quiz, "Chemistry", "-30th June", "Attempt"));
        holder.add(new model(R.drawable.quiz, "Chemistry", "-30th June", "Attempt"));
        holder.add(new model(R.drawable.quiz, "Chemistry", "-30th June", "Attempt"));
        holder.add(new model(R.drawable.quiz, "Chemistry", "-30th June", "Attempt"));
        holder.add(new model(R.drawable.quiz, "Chemistry", "-30th June", "Attempt"));
        holder.add(new model(R.drawable.quiz, "Chemistry", "-30th June", "Attempt"));

        return holder;




    }


   /* public ArrayList<model> dataque()
    {


        ArrayList<model> holder = new ArrayList<>();

        holder.add(new model(R.drawable.schoolbag, "Chemistry", "- Monika Agarwal", "Enroll"));

        holder.add(new model(R.drawable.schoolbag, "Physics", "- Monika Agarwal", "Enroll"));

        holder.add(new model(R.drawable.schoolbag, "Java", "- Monika Agarwal", "Enroll"));

        holder.add(new model(R.drawable.schoolbag, "Android", "- Monika Agarwal", "Enroll"));

        holder.add(new model(R.drawable.schoolbag, "Mathematics", "- Monika Agarwal", "Enroll"));

        holder.add(new model(R.drawable.schoolbag, "Electronics", "- Monika Agarwal", "Enroll"));


        return holder;
    }*/


}