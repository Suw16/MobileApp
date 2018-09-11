package com.suwat.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    Button googleLogout,SignUP,logIn;
    EditText Email,password;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton GoogleSignBtn;
    private static final int RC_SIGN_IN = 1;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Email = (EditText)findViewById(R.id.Email);
        password = (EditText)findViewById(R.id.password);
        logIn = (Button)findViewById(R.id.LogIn);
        GoogleSignBtn  = (SignInButton)findViewById(R.id.googleSigin);
        googleLogout = (Button)findViewById(R.id.Logout);
        SignUP =(Button)findViewById(R.id.next);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmail = Email.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                login(getEmail,getPassword );
            }
        });
      /* buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getEmail = email.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                if (getEmail.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();
                }if (getPassword.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Password ",Toast.LENGTH_SHORT).show();
                }else
                signUp(getEmail,getPassword);
            }
        });*/
        GoogleSignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

       /* googleLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });*/

        SignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent next = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(next);
            }
        });
    }
    /*
    private void login(){
        String getPhone = Email.getText().toString();
        final String getPassword = password.getText().toString();
        ref = FirebaseDatabase.getInstance().getReference().child("User");

        try {
            ref.child(getPhone).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Data newData = dataSnapshot.getValue(Data.class);
                    if (getPassword.equals(newData.Password)){
                        Toast.makeText(MainActivity.this, "Login Success ",Toast.LENGTH_SHORT).show();
                        Intent toMain = new Intent(MainActivity.this,Main3Activity.class);
                        startActivity(toMain);
                    }else {
                        Toast.makeText(MainActivity.this, "Password Doesn't match ",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }catch (Exception e){
            Toast.makeText(MainActivity.this, "Can't Login ",Toast.LENGTH_SHORT).show();
        }

    }*/

   /* private void signUp(String getEmail, String getPassword){
        mAuth.createUserWithEmailAndPassword(getEmail, getPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Register Success. ",Toast.LENGTH_SHORT).show();
                            Intent next = new Intent(MainActivity.this, Main2Activity.class);
                            startActivity(next);
                        } else {
                            Toast.makeText(MainActivity.this, "Can't use " + email, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {

            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent I = new Intent(getApplicationContext(), Main2Activity.class);
                            startActivity(I);
                        } else {
                            Toast.makeText(MainActivity.this,"you are not able to log in to google",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    private void login(String getEmail,String getPassword ){
        mAuth.signInWithEmailAndPassword(getEmail, getPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Intent Main = new Intent(MainActivity.this,Main3Activity.class);
                            startActivity(Main);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

    }


    private void updateUI(FirebaseUser user) {
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

           Toast.makeText(this, "Name of the user :" + personName, Toast.LENGTH_SHORT).show();
        }
    }
  /*  private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        // Google sign out
       mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                        Toast.makeText(MainActivity.this, "Logout Success... ",Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
}

