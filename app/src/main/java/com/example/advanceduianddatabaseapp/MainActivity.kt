package com.example.advanceduianddatabaseapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    //Create a variable of type DatabaseReference(provided by firebase):
    lateinit var database : DatabaseReference;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val signUpButton = findViewById<Button>(R.id.signUpButton);
        val uniqueId = findViewById<TextInputEditText>(R.id.uniqueId);
        val email = findViewById<TextInputEditText>(R.id.emailId);
        val password = findViewById<TextInputEditText>(R.id.password);
        val signInText = findViewById<TextView>(R.id.signInTV);

        signUpButton.setOnClickListener {
            //Getting and Storing all the credentials that user will input using text(getText) functionality:
            val uId = uniqueId.text.toString();
            val emailId = email.text.toString();
            val pass = password.text.toString();

            //Initialising the database var with the connection reference to firebase:
            //In simple words, establishing connection between kotlin backend and firebase DB.
            //Users is a database name where we'll store user credentials data:
            database = FirebaseDatabase.getInstance().getReference("Users")

            //Creating object of user data class to setValue of the attributes:
            val user = User(uId , emailId , pass);

            //Storing data into database:
            //Create a child that would recognise the particular record in DB:
            database.child(uId).setValue(user).addOnSuccessListener {
                //On Successful data storage execute a appropriate message:
                Toast.makeText(
                    this,
                    "User Registered Successfully!",
                    Toast.LENGTH_SHORT
                ).show();

                //Clear all the fields:
                uniqueId.text?.clear();
                email.text?.clear();
                password.text?.clear();


            }.addOnFailureListener {
                //On Failure in data storage execute a appropriate message:
                Toast.makeText(
                    this,
                    "User Registration Failed! Please Try Again!",
                    Toast.LENGTH_SHORT
                ).show();
            };


        }

    }
}