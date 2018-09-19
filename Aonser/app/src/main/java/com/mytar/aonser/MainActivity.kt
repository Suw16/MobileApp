package com.mytar.aonser

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Button.setOnClickListener {
            performRegister()


        }
        haveaccount.setOnClickListener {
            Log.d("Main","Try show login")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun performRegister(){

        val email = Email.text.toString()
        val password = Password.text.toString()

        if (email.isEmpty()|| password.isEmpty())
            Toast.makeText(this,"Plese enter your email/password",Toast.LENGTH_LONG).show()
        return

        Log.d("MainActivity","Email is:" + email)
        Log.d("MainActivity","Password: $password")

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("MainActivity","Sussessful: ${it.result.user.uid}")
                }
                .addOnFailureListener {
                    Log.d("MainActivity","Failed to create user: ${it.message}")
                }
    }
}
