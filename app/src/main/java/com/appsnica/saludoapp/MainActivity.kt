package com.appsnica.saludoapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializion preferencias dentro de onCreate
        preferencias = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE)
        guaradrPreferncias()
        val btnsaludar = findViewById<Button>(R.id.button)
        val txttexto = findViewById<TextView>(R.id.textView)

        btnsaludar.setOnClickListener{
            txttexto.text = "Hola Bienvenido ${recuperarPreferencia()}"

            //Invocamos a la siguiente actividad
            //val intent = Intent(this, SecondActivity::class.java)
            // startActivity(intent)
        }
    }
    private lateinit var preferencias: android.content.SharedPreferences

    fun guaradrPreferncias(){
        val editor = preferencias.edit()
        editor.putString("usuario", "Juan")
        editor.apply()
    }

    fun recuperarPreferencia(): String? {
        val usuario = preferencias.getString("usuario", "Invitado")
        return  usuario
    }
}