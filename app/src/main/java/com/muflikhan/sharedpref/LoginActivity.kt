package com.muflikhan.sharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    private lateinit var prefManager: PrefManager
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var username: String
    private lateinit var password: String

    /*DATA VALIDATION*/
    private var validUsername = "Muflikhan"
    private var validPassword = "1234"
    /*DATA VALIDATION*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
        checkLogin()
    }

    private fun init(){
        prefManager = PrefManager(this)
        etUsername = findViewById(R.id.et_username)
        etPassword = findViewById(R.id.et_password)
    }

    private fun checkLogin(){
        if (prefManager.isLogin()!!){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun clickLogin(view: View) {
        username = etUsername.text.toString().trim()
        password = etPassword.text.toString().trim()
        if (username.isEmpty() || username == ""){
            etUsername.error = "Username wajib diisi"
            etUsername.requestFocus()
        } else if (password.isEmpty() || password == ""){
            etPassword.error = "Password wajib diisi"
            etPassword.requestFocus()
        } else if (username != validUsername){
            etUsername.error = "Username belum terdaftar"
            etUsername.requestFocus()
        } else if (password != validPassword){
            etPassword.error = "Password tidak valid"
            etPassword.requestFocus()
        } else {
            prefManager.setLoggin(true)
            prefManager.setUsername(username)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


}