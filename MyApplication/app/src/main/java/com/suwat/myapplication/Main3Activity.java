package com.suwat.myapplication;

import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class Main3Activity extends AppCompatActivity {
    EditText getCarBrand,getCarID;
    Button CarBtn;
    FirebaseDatabase Database;
    DatabaseReference ref;
    Car AddCar = new Car();
    Data AddUser = new Data();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getCarBrand = (EditText)findViewById(R.id.carBrand);
        getCarID    = (EditText)findViewById(R.id.CarID);
        CarBtn      = (Button)findViewById(R.id.CarSubmit);

        Database    = FirebaseDatabase.getInstance();
        ref         = Database.getReference().child("Car");

        CarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddValue();
            }
        });
    }
    public void AddValue(){
        AddCar.setCarBrand(getCarBrand.getText().toString());
        AddCar.setCarID(getCarID.getText().toString());

        ref.child(AddCar.getCarBrand()).setValue(AddCar.CarID).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(Main3Activity.this,"Success. ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    //-------
}
