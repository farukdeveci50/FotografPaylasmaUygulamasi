package com.example.fotografpaylasmafirebase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.fotografpaylasmafirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage

class KullaniciActivity : AppCompatActivity() {
    private lateinit var tasarim : ActivityMainBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var firestore : FirebaseStorage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if(guncelKullanici != null){
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }


    }

    fun girisYap(view : View){
        auth.signInWithEmailAndPassword(tasarim.emailText.text.toString(),tasarim.passwordText.text.toString()).addOnSuccessListener {
            val guncelKullanici = auth.currentUser?.email.toString()
            Toast.makeText(this,"Hoşgeldin: ${guncelKullanici}",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener {
            Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    fun kayitOl(view : View){
        val email = tasarim.emailText.text.toString()
        val sifre = tasarim.passwordText.text.toString()

        auth.createUserWithEmailAndPassword(email,sifre).addOnSuccessListener {
            val intent = Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener {
            Toast.makeText(applicationContext,it.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }
}