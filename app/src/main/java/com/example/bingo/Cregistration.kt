package com.example.bingo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class Cregistration : AppCompatActivity() {

    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cregistration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firestore = FirebaseFirestore.getInstance()

        val registerButton: Button = findViewById(R.id.button)
        registerButton.setOnClickListener {
            saveDataToFirestore()
        }
    }

    private fun saveDataToFirestore() {
        val committeeName = findViewById<EditText>(R.id.committeeNameEt).text.toString()
        val organizationName = findViewById<EditText>(R.id.organizationNameEt).text.toString()
        val establishedSince = findViewById<EditText>(R.id.establishedSinceEt).text.toString()
        val email = findViewById<EditText>(R.id.emailEt).text.toString()
        val establishedLocation = findViewById<EditText>(R.id.establishedLocationEt).text.toString()
        val contactInfo = findViewById<EditText>(R.id.contactInfoEt).text.toString()
        val socialMedia = findViewById<EditText>(R.id.socialMediaEt).text.toString()
        val description = findViewById<EditText>(R.id.descriptionEt).text.toString()

        val registrationData = hashMapOf(
            "committeeName" to committeeName,
            "organizationName" to organizationName,
            "establishedSince" to establishedSince,
            "email" to email,
            "establishedLocation" to establishedLocation,
            "contactInfo" to contactInfo,
            "socialMedia" to socialMedia,
            "description" to description
        )

        firestore.collection("registrations")
            .add(registrationData)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Registered successfully", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}