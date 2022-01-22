package me.dio.estacaohackapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import me.dio.estacaohackapp.databinding.ActivityLoginBinding
import java.util.*

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginLogin.setOnClickListener {
            val email = binding.edtLoginEmail.text.toString().trim().lowercase()
            val password = binding.edtLoginPassword.text.toString().trim()

            if (email.isEmpty()) {
                binding.edtLoginEmail.error = "O email é obrigatório"
                binding.edtLoginEmail.requestFocus()
            } else if (password.isEmpty()) {
                binding.edtLoginPassword.error = "A senha é obrigatória"
                binding.edtLoginPassword.requestFocus()
            } else {
                val sharedPrefs = getSharedPreferences(
                    "cadastro_$email",
                    Context.MODE_PRIVATE
                )

                val emailPrefs = sharedPrefs.getString("EMAIL", "")
                val passwordPrefs = sharedPrefs.getString("PASSWORD", "")

                if (email == emailPrefs && password == passwordPrefs) {
                    Toast.makeText(this,"Login Successful", Toast.LENGTH_LONG).show()
                    val mIntent = Intent(this, MainActivity::class.java)
                    mIntent.putExtra("INTENT_EMAIL", email)
                    startActivity(mIntent)
                    finish()
                } else {
                    Toast.makeText(this,"Login failed", Toast.LENGTH_LONG).show()
                    binding.edtLoginEmail.setText("")
                    binding.edtLoginPassword.setText("")
                }
            }

        }

        binding.btnLoginSignUp.setOnClickListener {
            val mIntent = Intent(this, RegisterActivity::class.java)
            startActivity(mIntent)
        }
    }
}