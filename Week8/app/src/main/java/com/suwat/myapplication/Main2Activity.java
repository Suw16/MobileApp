package com.suwat.myapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText getFname,getLname,getPhone,getIden,getPassword,getEmail;
    Button Submit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    User addUser = new User();
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//--------------------------------------------------- Attribute ---------
        getEmail = (EditText)findViewById(R.id.Email);
        getFname = (EditText)findViewById(R.id.Fname);
        getLname = (EditText)findViewById(R.id.Lname);
        getIden = (EditText)findViewById(R.id.Iden);
        getPhone  = (EditText)findViewById(R.id.Phone);
        getPassword = (EditText)findViewById(R.id.Password);
        Submit = (Button)findViewById(R.id.Submit);

//-------------------------------------------------- FireBase -----------
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        myRef = database.getReference().child("User");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String EmailRegis = getEmail.getText().toString().trim();
                String PasswordRegis = getPassword.getText().toString().trim();
                Register(EmailRegis,PasswordRegis);
            }
        });

    }
    public void Register(String EmailRegis,String PasswordRegis){
        addUser.setEmail(getEmail.getText().toString());
        addUser.setFname(getFname.getText().toString());
        addUser.setLname(getLname.getText().toString());
        addUser.setID(getIden.getText().toString());
        addUser.setPhone(getPhone.getText().toString());
        addUser.setPassword(getPassword.getText().toString());

        mAuth.createUserWithEmailAndPassword(EmailRegis,PasswordRegis).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                myRef.child(mAuth.getCurrentUser().getUid()).setValue(addUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(Main2Activity.this,"Register Success. ",Toast.LENGTH_LONG).show();
                        Intent backtoIndex = new Intent(Main2Activity.this,MainActivity.class);
                        startActivity(backtoIndex);
                    }
                });
            }
        });
    }
    //--------------------------------------------------------------
}
