package com.mytar.mytar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button btn;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener stateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            if (firebaseAuth.getCurrentUser() !=null){
                startActivity(new Intent(MainActivity.this, loginActivity.class));
            }
            }
        };


        email = findViewById(R.id.e_email);
        password = findViewById(R.id.e_pass);
        btn = findViewById(R.id.btn_go);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }
        @Override
        protected void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(stateListener);
        }

        public void signIn(){

            final String Email = email.getText().toString();
            final String Pass = password.getText().toString();

            if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(Pass)){
                Toast.makeText(MainActivity.this,"kuy" ,Toast.LENGTH_SHORT).show();
            }
            else {
                firebaseAuth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful()){
                            Toast.makeText(MainActivity.this,"hee" ,Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this,"tad" ,Toast.LENGTH_SHORT).show();
                    }}
                });
            }

        }
    }

