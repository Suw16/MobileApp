package com.suwat.myapplication;


import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Main3Activity extends AppCompatActivity {

    TextView textnameUser;
    FirebaseAuth mAuth;
    FirebaseUser getuser;
    DatabaseReference showUser;
    FirebaseDatabase database;
    FirebaseAuth.AuthStateListener Authlisten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textnameUser = (TextView)findViewById(R.id.userName);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        getuser = mAuth.getCurrentUser();
        showUser = database.getReference("User");
        Authlisten = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(getuser != null){

                }
            }
        };


        if (getuser != null) {
            UserInfo();
        }
    }

    private void UserInfo(){
        showUser.child(getuser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User userInfo = dataSnapshot.getValue(User.class);
                assert userInfo != null;
                String username = userInfo.getFname();
                textnameUser.setText(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(Authlisten);
    }
//-------
}
