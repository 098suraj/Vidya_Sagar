package com.example.guru_cares.activityclass;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guru_cares.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public String studentcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        TextView loginbtn = (TextView) findViewById(R.id.loginbtn);
        mAuth = FirebaseAuth.getInstance();


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginbtn.setBackground(ContextCompat.getDrawable(getBaseContext(),R.drawable.blackoutlinebg));

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.isEmpty() || pass.isEmpty())
                {
                    Toast.makeText(login.this, "null", Toast.LENGTH_SHORT).show();
                }

                else
                {


                    mAuth.signInWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        studentcode = username.getText().toString().substring(0,3);

                                        if(studentcode.equals("200"))
                                        {
                                            Intent i = new Intent(login.this, gradelists.class);
                                            i.putExtra("username", user);
                                            i.putExtra("userid", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                                            i.putExtra("gradename", "Grade");
                                            i.putExtra("sectionname", "Section");
                                            i.putExtra("check", "false");
                                            i.putExtra("position", 99);
                                            startActivity(i);

                                        }
                                        else if(studentcode.equals("100"))
                                        {
                                            Intent i = new Intent(login.this, MainActivity.class);
                                            i.putExtra("username", user);
                                            i.putExtra("userid", Objects.requireNonNull(mAuth.getCurrentUser()).getUid());
                                            i.putExtra("gradename", "Grade");
                                            i.putExtra("sectionname", "Section");
                                            startActivity(i);

                                        }


                                        Toast.makeText(login.this, "Sucessfully Logged In", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(login.this, "Failed to Logged In", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });





                }




            }
        });



    }
}