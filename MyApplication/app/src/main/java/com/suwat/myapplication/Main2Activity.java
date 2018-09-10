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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main2Activity extends AppCompatActivity {

    EditText getFname,getLname,getPhone,getIden,getPssword;
    Button Submit;
    FirebaseDatabase database;
    DatabaseReference myRef;
    Data newData = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getFname = (EditText)findViewById(R.id.Fname);
        getLname = (EditText)findViewById(R.id.Lname);
        getIden = (EditText)findViewById(R.id.Iden);
        getPhone  = (EditText)findViewById(R.id.Phone);
        getPssword = (EditText)findViewById(R.id.Password);
        Submit = (Button)findViewById(R.id.Submit);
        database = FirebaseDatabase.getInstance();

        myRef = database.getReference().child("User");

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
            }
        });
    }
    public void getValue(){
        newData.setFirstName(getFname.getText().toString());
        newData.setLastName(getLname.getText().toString());
        newData.setIdentify(getIden.getText().toString());
        newData.setPhoneNumber(getPhone.getText().toString());
        newData.setPassword(getPssword.getText().toString());

        myRef.child(newData.getFirstName()).setValue(newData).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Intent Done = new Intent(Main2Activity.this,MainActivity.class);
                    startActivity(Done);
                    Toast.makeText(Main2Activity.this, "Register Succcess ",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Main2Activity.this,"Can't Register ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
