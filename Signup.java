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

import com.example.amaranathyatra.thinkgrab.network.Apiclient;
import com.example.amaranathyatra.thinkgrab.network.networkapi.Signupnetworkapi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//signup updated
public class Signup extends AppCompatActivity {

    Button bt1;
    EditText email,name,pass,confpass;
    ProgressBar progressBar;
    TextView already_signupp;

	//FirebaseFirestore instance 
    FirebaseFirestore firebaseFirestoredb;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        bt1= findViewById(R.id.btn_signup);
        email=findViewById(R.id.email_signup);
        name=findViewById(R.id.name_signup);
        pass=findViewById(R.id.pass_signup);
        confpass=findViewById(R.id.conf_pass_signup);
        progressBar=findViewById(R.id.progressBar);
        already_signupp= findViewById(R.id.already_signup);



        firebaseFirestoredb=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();

          fetchSignupData();

         if(firebaseAuth.getCurrentUser() !=null)
         {
             Intent in = new Intent(Signup.this,Navigation.class);
             startActivity(in);
             finish();
         }



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emaaill=email.getText().toString();
                String namee=name.getText().toString();
                String passs=pass.getText().toString();
                String conf_passe=confpass.getText().toString();
                if(TextUtils.isEmpty(emaaill)){
                    email.setError("email is required");
                    return;
                }
                if(TextUtils.isEmpty(namee)){
                    name.setError("name is required");
                    return;
                }
                if(TextUtils.isEmpty(passs)){
                    pass.setError("password is required");
                    return;
                }
                if(passs.length() < 8){
                    pass.setError("password length must be of greater than or equal to 8 length is required");
                    return;
                }
                if(TextUtils.isEmpty(conf_passe)){
                    confpass.setError("confirm password is required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                submitSignupData();
                firebaseAuth.createUserWithEmailAndPassword(emaaill,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                            Toast.makeText(Signup.this, "You have been register successfully", Toast.LENGTH_SHORT).show();
                            DocumentReference documentReference = firebaseFirestoredb.collection("Users").document(firebaseUser.getUid());
                            Map<String,Object> userreginfo = new HashMap<>();
                            userreginfo.put("Email",email.getText().toString());
                            userreginfo.put("FullName",name.getText().toString());
                            userreginfo.put("Pass",pass.getText().toString());
                            userreginfo.put("ConfPass",confpass.getText().toString());
                            documentReference.set(userreginfo);
                            Intent in = new Intent(Signup.this,Navigation.class);
                            startActivity(in);
                        }
                        else { Toast.makeText(Signup.this, "You have been encountered an error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




              //  sendSignupData(emaaill,namee,passs,conf_passe);

            }
        });

         already_signupp.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent in = new Intent(Signup.this,Login.class);
                 startActivity(in);
             }
         });


    }
//    public  void sendSignupData(final String email, String name, final String pass, String conf_pass){
//        SignupModel signupModel = new SignupModel(email,name,pass,conf_pass);
//        firebaseFirestoredb.collection("Signup")
//                .add(signupModel)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(getApplicationContext(),"You have been Signup Sucessfully",Toast.LENGTH_SHORT).show();
//                        sendLoginData(email,pass);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(),"You have encountered an error"+e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//
//
//
//
//    }
//    public void sendLoginData(String emailsignin, String pass_signin) {
//        LoginModel loginModel = new LoginModel(emailsignin, pass_signin);
//        firebaseFirestoredb.collection("SignIn")
//                .add(loginModel)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                       // Toast.makeText(getApplicationContext(), "You have been Login Sucessfully", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                       // Toast.makeText(getApplicationContext(), "You have encountered an error" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//    }



    public  void submitSignupData(){
        SignupModel signupModel = new SignupModel(email.getText().toString(),name.getText().toString(),pass.getText().toString(),confpass.getText().toString());
        Call<JSONObject> jsonObjectCall = new  Apiclient().getRetrofit().create(Signupnetworkapi.class).insertSignupData(signupModel);
        jsonObjectCall.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {

                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "You have been signup successfully", Toast.LENGTH_SHORT).show();
                Intent in = new Intent(Signup.this,Navigation.class);
                startActivity(in);
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "You have encountered an error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }


    public  void fetchSignupData(){
        Call<List<SignupModel>> Call = new  Apiclient().getRetrofit().create(Signupnetworkapi.class).fetchSignupData();
        Call.enqueue(new Callback<List<SignupModel>>() {
            @Override
            public void onResponse(Call<List<SignupModel>> call, Response<List<SignupModel>> response) {
                progressBar.setVisibility(View.GONE);
                List<SignupModel> modelList =response.body();
                if(modelList!=null && !modelList.isEmpty()) {
                    for (SignupModel signupModel : modelList) {
                        String data = "";
                        data += "email:" + signupModel.getEmail() + "\n";
                        data += "name:" + signupModel.getName() + "\n";
                        data += "pass:" + signupModel.getPass() + "\n";
                        data += "conf_pass:" + signupModel.getConf_pass() + "\n\n";



                    }
                }
                //Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<List<SignupModel>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "You have encountered an error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });





    }
}
