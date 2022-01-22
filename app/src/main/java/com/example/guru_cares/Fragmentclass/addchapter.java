package com.example.guru_cares.Fragmentclass;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guru_cares.R;
import com.example.guru_cares.activityclass.MainActivity;
import com.example.guru_cares.modelclass.chapter_model;
import com.example.guru_cares.modelclass.subject_model;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addchapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addchapter extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int check_for_browsebtn = 1;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView removepdfbtn, pdfpic,browsepic;
    TextView browsebtn, addbtn;
    EditText chapterdescription, chaptertitle;

    String schoolname, sectioncode , gradecode, studentcode, subjectname;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    int nchapters;

    Uri filepath;

    public addchapter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addchapter.
     */
    // TODO: Rename and change types and number of parameters
    public static addchapter newInstance(String param1, String param2) {
        addchapter fragment = new addchapter();
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
        View v = inflater.inflate(R.layout.fragment_addchapter, container, false);


        Bundle bundle = this.getArguments();
        schoolname = bundle.getString("schoolname");
        sectioncode = bundle.getString("sectioncode");
        gradecode = bundle.getString("gradecode");
        studentcode = bundle.getString("studentcode");
        subjectname = bundle.getString("subjectname");

        removepdfbtn = (ImageView) v.findViewById(R.id.removepdfbtn);
        pdfpic = (ImageView) v.findViewById(R.id.pdfpic);
        browsepic = (ImageView) v.findViewById(R.id.browsepic);
        browsebtn = (TextView) v.findViewById(R.id.browsebtn);
        chaptertitle = (EditText) v.findViewById(R.id.titlename);
        chapterdescription = (EditText) v.findViewById(R.id.description);
        addbtn = (TextView) v.findViewById(R.id.addbtn);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("uploadPdf");


        addbtn.setVisibility(View.INVISIBLE);

        browsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addbtn.setVisibility(View.VISIBLE);
                selectPdf();
            }
        });




        removepdfbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pdfpic.setVisibility(View.GONE);
                browsepic.setVisibility(View.VISIBLE);
                browsebtn.setVisibility(View.VISIBLE);
                removepdfbtn.setVisibility(View.GONE);
                //data.setData(null);
                addbtn.setVisibility(View.INVISIBLE);




            }
        });



        FirebaseDatabase database =  FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference().child("schools").child("trmps").child("100").child(gradecode).child("subjects").child(subjectname);
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        subject_model model = snapshot.getValue(subject_model.class);
                         nchapters = model.getNchapters();

                        //Toast.makeText(getContext(), " " + nchapters, Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



        return v;
    }


    private void selectPdf() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"PDF File Selected"),12);

    }





    private void uploadPDfFileFirebase(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("File Loading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("upload"+System.currentTimeMillis()+".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (! uriTask.isComplete());
                        Uri uri = uriTask.getResult();

                        //insertPDF insertPDF = new insertPDF(editText.getText().toString(),uri.toString());
                        chapter_model model = new chapter_model(chaptertitle.getText().toString(), chapterdescription.getText().toString(), uri.toString());
                        //databaseReference.child(databaseReference.push().getKey()).setValue(model);

                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                        databaseReference = db.getReference().child("schools").child(schoolname).child("100").child(gradecode).child("subjects").child(subjectname);
                        databaseReference.child("chapters").child("chapter"+(nchapters+1)).setValue(model);

                        FirebaseDatabase database =  FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference().child("schools").child("trmps").child("100").child(gradecode).child("subjects").child(subjectname);
                        ref.child("nchapters").setValue(nchapters+1);


                        Toast.makeText(getContext(),"File Uploaded Successfully",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("File Uploading.."+(int)progress+"%");


            }
        });

        browsepic.setVisibility(View.VISIBLE);
        removepdfbtn.setVisibility(View.GONE);
        pdfpic.setVisibility(View.GONE);
        browsebtn.setVisibility(View.VISIBLE);


        Toast.makeText(getContext(), "pdf uploading", Toast.LENGTH_SHORT).show();
    }


    /*public void downloadpdfbutton(View view) {
        startActivity(new Intent(getContext(),DownloadPdfActivity.class));
    }*/





@Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 12 && resultCode == getActivity().RESULT_OK && data!=null && data.getData()!=null)
        {
            //uploadbutton.setEnabled(true);
            //editText.setText(data.getDataString().substring(data.getDataString().lastIndexOf("/")+1));

            addbtn.setVisibility(View.VISIBLE);
            browsepic.setVisibility(View.GONE);
            removepdfbtn.setVisibility(View.VISIBLE);
            pdfpic.setVisibility(View.VISIBLE);
            browsebtn.setVisibility(View.GONE);


            addbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //uploadbutton.setBackground(ContextCompat.getDrawable(getBaseContext(),R.drawable.darkblueoutline));
                    addbtn.setVisibility(View.INVISIBLE);
                    uploadPDfFileFirebase(data.getData());
                }
            });
        }
        else
        {
            Toast.makeText(getContext(), "Select a pdf file to upload", Toast.LENGTH_SHORT).show();
        }






    }


}