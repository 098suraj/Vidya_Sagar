package com.example.guru_cares.activityclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guru_cares.R;
import com.example.guru_cares.customadapters.gradelist_customadapter;
import com.example.guru_cares.modelclass.grade_model;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class gradelists extends AppCompatActivity {

    public RecyclerView gradelist;
    public gradelist_customadapter gradeadapter;
    public String check = "false";
    public int position = 99;
    public String gradename = "Grade";
    public String userid, username;
    public String sectionname = "Section";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradelists);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        userid =(String) bundle.get("userid");
        username = (String) bundle.get("username");
        check = (String) bundle.get("check");
        position = (int) bundle.get("position");
        gradename =(String) bundle.get("gradename");
        sectionname = (String) bundle.get("sectionname");
        // sectionname = sectionlist_customadapter.sectionname;





        /*gradelist = (RecyclerView) v.findViewById(R.id.recyclergradelist);
        gradelist.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
        gradeadapter = new gradelist_custom_adapter(dataque_task(), getContext(), check, position);
        gradelist.setAdapter(gradeadapter);*/


        gradelist = (RecyclerView) findViewById(R.id.recyclergradelist);
        gradelist.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

        FirebaseRecyclerOptions<grade_model> options=
                new FirebaseRecyclerOptions.Builder<grade_model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("schools").child("trmps").child("grades"), grade_model.class)
                        .build();

        gradeadapter = new gradelist_customadapter(options,this, check, position, userid , username, gradename,sectionname);
        gradelist.setAdapter(gradeadapter);


        ImageView next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //pending
                if(gradename.equals("Grade") || sectionname.equals("Section"))
                {

                    Toast.makeText(gradelists.this, "You are not eligible", Toast.LENGTH_SHORT).show();
                }
                else
                {

                    Bundle bundle = new Bundle();
                    bundle.putString("gradename", gradename);
                    bundle.putString("sectionname", sectionname);

                    /*AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fragment myFragment = new Home();
                    myFragment.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment, myFragment).addToBackStack(null).commit();*/

                    Toast.makeText(gradelists.this, "You are eligible", Toast.LENGTH_SHORT).show();


                }


            }
        });





















    }


    @Override
    public void onStart() {
        super.onStart();
        gradeadapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        gradeadapter.stopListening();
    }

}