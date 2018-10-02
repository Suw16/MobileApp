package com.suwat.myapplication;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
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
    DatabaseReference showUser,getPost;
    FirebaseDatabase database;
    EditText header,detail;
    FirebaseAuth.AuthStateListener Authlisten;
    Button submitPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textnameUser = (TextView)findViewById(R.id.userName);
        submitPost = (Button)findViewById(R.id.submitPost);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        getuser = mAuth.getCurrentUser();
        header = (EditText) findViewById(R.id.header);
        detail = (EditText)findViewById(R.id.detail);
        showUser = database.getReference("User");

        //----------------------------------------------------------------
        if (getuser != null) {
            UserInfo();
        }

        submitPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postNow();
            }
        });
     //----------------------
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

    private void postNow(){
        getPost = database.getReference("Post");
        Post newpost = new Post();
        String node = getPost.child(newpost.headers).push().getKey();

        newpost.setHeader(header.getText().toString());
        newpost.setDetails(header.getText().toString());

        getPost.child(node).setValue(newpost.details).addOnCanceledListener(new OnCanceledListener() {
            @Override
            public void onCanceled() {
                Toast.makeText(Main3Activity.this,"Success",Toast.LENGTH_SHORT).show();
            }
        });

    }
    //-------
}
