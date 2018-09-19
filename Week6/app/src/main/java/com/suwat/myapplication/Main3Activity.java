package com.suwat.myapplication;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;

public class Main3Activity extends AppCompatActivity {

    TextView textemailUser,textnameUser;
    FirebaseAuth mAuth;
    FirebaseUser getuser;
    DatabaseReference showUser;
    FirebaseDatabase database;
    //User userinfo = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textemailUser = (TextView) findViewById(R.id.emailUser);
        textnameUser = (TextView)findViewById(R.id.userName);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        getuser = mAuth.getCurrentUser();
        showUser = database.getReference("User");


        if (getuser != null) {
            String email = getuser.getUid();
            textemailUser.setText(email);
            UserInfo();
        }
    }

    private void UserInfo(){
        showUser.child(getuser.getUid()).child("fname").addValueEventListener(new ValueEventListener() {
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

    //-------
}
