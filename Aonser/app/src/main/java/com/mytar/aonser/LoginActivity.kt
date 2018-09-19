package com.mytar.aonser

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Login.setOnClickListener {
            val email = Emaillogin.text.toString()
            val password = passwordlogin.text.toString()


        }
        Backregister.setOnClickListener{
            finish()
        }

    }
}