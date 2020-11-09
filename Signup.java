package com.example.amaranathyatra.thinkgrab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Signup extends AppCompatActivity {

    Button bt1;
    EditText email,name,pass,confpass;
	//FirebaseFirestore instance 
    FirebaseFirestore firebaseFirestoredb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bt1= findViewById(R.id.btn_signup);
        email=findViewById(R.id.email_signup);
        name=findViewById(R.id.name_signup);
        pass=findViewById(R.id.pass_signup);
        confpass=findViewById(R.id.conf_pass_signup);
        firebaseFirestoredb=FirebaseFirestore.getInstance();





        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emaaill=email.getText().toString();
                String namee=name.getText().toString();
                String passs=pass.getText().toString();
                String conf_passe=confpass.getText().toString();
                sendSignupData(emaaill,namee,passs,conf_passe);
                Intent in = new Intent(Signup.this,Login.class);
                startActivity(in);
            }
        });

    }
    public  void sendSignupData(String email,String name,String pass,String conf_pass){
        SignupModel signupModel = new SignupModel(email,name,pass,conf_pass);
        firebaseFirestoredb.collection("Signup")
                .add(signupModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"You have been Signup Sucessfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"You have encountered an error"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });





    }
}
