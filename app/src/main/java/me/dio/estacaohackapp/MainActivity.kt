package me.dio.estacaohackapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import me.dio.estacaohackapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val email = intent.getStringExtra("INTENT_EMAIL")

        val sharedPrefs = getSharedPreferences(
            "cadastro_$email",
            Context.MODE_PRIVATE
        )

        val namePrefs = sharedPrefs.getString("FULL_NAME", "")
        val emailPrefs = sharedPrefs.getString("EMAIL", "")
        val continentPrefs = sharedPrefs.getString("CONTINENT", "")

        binding.tvMainName.text = namePrefs
        binding.tvMainEmail.text = emailPrefs
        binding.tvMainContinent.text = continentPrefs

        binding.btnMainQuit.setOnClickListener {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Atenção!")
            alert.setMessage("Deseja mesmo sair?")
            alert.setPositiveButton("Sair") { dialog, which ->
                val mIntent = Intent(this, LoginActivity::class.java)
                startActivity(mIntent)
                finishAffinity()
            }
            alert.setNeutralButton("Não") { dialog, which -> }
            alert.setCancelable(false)
            alert.show()
        }

        binding.btnMainSiteCellep.setOnClickListener {
            val mIntent = Intent(this, WebActivity::class.java)
            startActivity(mIntent)
        }
    }
}