package com.example.guru_cares.Fragmentclass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.guru_cares.R;
import com.example.guru_cares.modelclass.NoticeData_Model;
import com.example.guru_cares.timeTable.MainActivityCalendar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminFragment extends Fragment {

    EditText noticeTitle,noticeData;
    Button sendNotice;

    DatabaseReference noticeDbRef;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdminFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdminFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdminFragment newInstance(String param1, String param2) {
        AdminFragment fragment = new AdminFragment();
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
        View view=inflater.inflate(R.layout.fragment_admin, container, false);
        noticeTitle=view.findViewById(R.id.noticeTitle);
        noticeData=view.findViewById(R.id.noticeText);
        sendNotice=view.findViewById(R.id.sendNotice);

        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insetNoticeData();


            }

        });



        noticeDbRef=FirebaseDatabase.getInstance().getReference().child("Notices");

        return view;
    }

    private void insetNoticeData() {
        String Title= noticeTitle.getText().toString();
        String noticeMessage=noticeData.getText().toString();

        NoticeData_Model model= new NoticeData_Model(Title,noticeMessage);

        if(TextUtils.isEmpty(Title) || TextUtils.isEmpty(noticeMessage))
        {
            Toast.makeText(getContext(), "Please enter all the Details", Toast.LENGTH_SHORT).show();
            return;
        }

        else {
            noticeDbRef.push().setValue(model);
            noticeTitle.setText("");
            noticeData.setText("");
            Toast.makeText(getContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
        }





    }
}