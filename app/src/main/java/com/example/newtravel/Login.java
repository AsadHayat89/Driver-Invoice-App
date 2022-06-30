package com.example.newtravel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private Button SignUpp,SignIn;
    private EditText email,pass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intwidget();
        callListiner();
    }

    private void callListiner() {
    SignIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent i=new Intent(Login.this,SignUp.class);
            startActivity(i);
        }
    });
    SignUpp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String Email = email.getText().toString();
            String Pass = pass.getText().toString();
            if(TextUtils.isEmpty(Pass)){
                pass.setError("Email cannot be empty");
                return;
            }
            else if(TextUtils.isEmpty(Email))
            {
                email.setError("Email cannot be empty");
                return;
            }
            else{


                mAuth.signInWithEmailAndPassword(Email, Pass)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Toast.makeText(Login.this,"Successflur "+mAuth.getCurrentUser().getEmail(),Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(Login.this, Menu.class);
                                    startActivity(in);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    pass.setError("Invalid Password");
                                 //   Toast.makeText(Login.this, "Authentication failed.",
                                   //         Toast.LENGTH_SHORT).show();

                                }

                                // ...
                            }
                        });
            }



        }

    });
    }

    private void intwidget() {
        SignIn=findViewById(R.id.btnSign);
        SignUpp=findViewById(R.id.btnLoginIn);
        email=findViewById(R.id.txtChkEmail);
        pass=findViewById(R.id.txtChkPassword);
        mAuth=FirebaseAuth.getInstance();
    }
}