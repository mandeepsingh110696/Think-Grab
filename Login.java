package com.example.amaranathyatra.thinkgrab;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


//login class
public class Login extends AppCompatActivity {

    EditText email_signin, pass_signin;
    TextView dont_have_signinn;
    ProgressBar progressBar;

  //  FirebaseFirestore firebaseFirestoredb;
  FirebaseAuth firebaseAuth;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_signin = findViewById(R.id.email_signin);
        pass_signin = findViewById(R.id.pass_signin);
        //firebaseFirestoredb = FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        dont_have_signinn = findViewById(R.id.dont_have_signin);
        progressBar=findViewById(R.id.progressBar);

        btn_login = findViewById(R.id.btn_signin);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_signinn=email_signin.getText().toString();
                String pass_signinn=pass_signin.getText().toString();
                if(TextUtils.isEmpty(email_signinn)) {
                    email_signin.setError("email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass_signinn)){
                    pass_signin.setError("password is required");
                    return;
                }
                if(pass_signinn.length() < 8){
                    pass_signin.setError("password length must be of greater than or equal to 8 length is required");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                firebaseAuth.signInWithEmailAndPassword(email_signinn,pass_signinn).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "You have been Logged in successfully", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(Login.this,Navigation.class);
                            startActivity(in);
                        }
                        else { Toast.makeText(Login.this, "You have been encountered an error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                // sendLoginData(email_signinn,pass_signinn);

            }
        });

        dont_have_signinn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Login.this,Signup.class);
                startActivity(in);
            }
        });
    }

//    public void sendLoginData(String emailsignin, String pass_signin) {
//        LoginModel loginModel = new LoginModel(emailsignin, pass_signin);
//        firebaseFirestoredb.collection("SignIn")
//                .add(loginModel)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(), "You have been Login Sucessfully", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), "You have encountered an error" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }
}
