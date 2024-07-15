package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogIn: Button
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
       edtEmail=findViewById(R.id.edt_email)
        edtPassword=findViewById(R.id.edt_password)
       btnLogIn=findViewById(R.id.btn_login)
        btnSignUp=findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener{
            val intent=Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnLogIn.setOnClickListener{
            val email= edtEmail.text.toString()
            val password= edtPassword.text.toString()
            login(email,password)
        }


    }
    private fun login(email:String, password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@LogIn, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@LogIn, "User does not exist",Toast.LENGTH_SHORT).show()
                }
            }
    }
}