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

public class Login extends AppCompatActivity {

    EditText email_signin, pass_signin;

    FirebaseFirestore firebaseFirestoredb;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_signin = findViewById(R.id.email_signin);
        pass_signin = findViewById(R.id.pass_signin);
        firebaseFirestoredb = FirebaseFirestore.getInstance();


        btn_login = findViewById(R.id.btn_signin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_signinn=email_signin.getText().toString();
                String pass_signinn=pass_signin.getText().toString();
                sendLoginData(email_signinn,pass_signinn);
                Intent loginintent = new Intent(Login.this, Navigation.class);
                startActivity(loginintent);
            }
        });
    }

    public void sendLoginData(String emailsignin, String pass_signin) {
        LoginModel loginModel = new LoginModel(emailsignin, pass_signin);
        firebaseFirestoredb.collection("SignIn")
                .add(loginModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "You have been Login Sucessfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "You have encountered an error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
