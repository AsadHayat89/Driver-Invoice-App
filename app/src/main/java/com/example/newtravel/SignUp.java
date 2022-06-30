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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    private EditText Email,Password,address,Name,phone,NiF;
    private Button Signup;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore fstore;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intiwidget();
        callListiner();
    }

    private void callListiner() {
    Signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String email = Email.getText().toString();
            String password = Password.getText().toString();
            String uName=Name.getText().toString();
            String uAddress=address.getText().toString();
            String uphone=phone.getText().toString();
            String nf=NiF.getText().toString();

            if (TextUtils.isEmpty(email)) {
                Email.setError("Email cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(uName)) {
                Name.setError("Name cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(uAddress)) {
                address.setError("Address cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(uphone)) {
                phone.setError("Phone cannot be empty");
                return;
            }
            if (TextUtils.isEmpty(nf)) {
                NiF.setError("NiF cannot be empty");
                return;
            }
            else if (TextUtils.isEmpty(password)) {
                Password.setError("Password cannot be empty");
                return;
            }

            else if (password.length()<8) {
                Password.setError("Password length should not be less than 6");
                return;
            }

                //Toast.makeText(SignUp.this,email+" "+password,Toast.LENGTH_SHORT).show();
                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //Toast.makeText(SignUp.this,"Status  " +task.isSuccessful(),Toast.LENGTH_SHORT).show();
                        if (task.isSuccessful()) {
                            /**String UserID=firebaseAuth.getCurrentUser().getUid();
                            //Toast.makeText(SignUp.this,UserID,Toast.LENGTH_LONG).show();
                            DocumentReference ref=fstore.collection("Users").document(UserID);
                            Map<String,Object> m=new HashMap<>();
                            m.put("Name",uName);
                            m.put("Address",uAddress);
                            m.put("Phone",uphone);
                            m.put("NIF",nf);
                            ref.set(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(SignUp.this,"Register Succesfully",Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp.this,"Register Failed",Toast.LENGTH_LONG).show();
                                }
                            });**/

                            User user = new User(uName,uphone,nf,uAddress);
                            String UserID=firebaseAuth.getCurrentUser().getUid();
                            mDatabase.child("users").child(UserID).setValue(user);
// ...

                            Intent i=new Intent(SignUp.this,Login.class);
                            startActivity(i);
                            //finish();
                        } else {
                            //Log.w("Tag", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }

    });
    }

    private void intiwidget() {
        Email=findViewById(R.id.txtEamil);
        Password=findViewById(R.id.txtpassword);
        address=findViewById(R.id.txtAddress);
        Name=findViewById(R.id.txtName);
        phone=findViewById(R.id.txtPhone);
        NiF=findViewById(R.id.txtNif);
        Signup=findViewById(R.id.bntSignuup);
        fstore=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
}