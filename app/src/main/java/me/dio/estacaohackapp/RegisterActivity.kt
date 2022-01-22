package me.dio.estacaohackapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import me.dio.estacaohackapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerContinents = binding.spnRegisterContinent

        ArrayAdapter.createFromResource(
            this,
            R.array.continents_array,
            android.R.layout.simple_spinner_item
        ). also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerContinents.adapter = adapter
        }

        binding.btnRegisterRegister.setOnClickListener {
            val firstName = binding.edtRegisterFirstName.text.toString().trim()
            val lastName = binding.edtRegisterLastName.text.toString().trim()
            val email = binding.edtRegisterEmail.text.toString().trim().lowercase()
            val password = binding.edtRegisterPassword.text.toString().trim()
            val continent = binding.spnRegisterContinent.selectedItem.toString()

            when {
                firstName.isEmpty() -> {
                    binding.edtRegisterFirstName.error = "O nome é obrigatório"
                    binding.edtRegisterFirstName.requestFocus()
                }
                lastName.isEmpty() -> {
                    binding.edtRegisterLastName.error = "O sobrenome é obrigatório"
                    binding.edtRegisterLastName.requestFocus()
                }
                email.isEmpty() -> {
                    binding.edtRegisterEmail.error = "O email é obrigatório"
                    binding.edtRegisterEmail.requestFocus()
                }
                password.isEmpty() -> {
                    binding.edtRegisterPassword.error = "A senha é obrigatória"
                    binding.edtRegisterPassword.requestFocus()
                }
                else -> {
                    val sharedPref = getSharedPreferences("cadastro_$email", Context.MODE_PRIVATE)
                    with (sharedPref.edit()) {
                        putString("FULL_NAME", "$firstName $lastName")
                        putString("EMAIL", email)
                        putString("PASSWORD", password)
                        putString("CONTINENT", continent)
                        apply()
                    }
                    Toast.makeText(this,"Conta Cadastrada", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}