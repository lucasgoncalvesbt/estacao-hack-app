package me.dio.estacaohackapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import me.dio.estacaohackapp.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.wbvWeb.settings.javaScriptEnabled = true
        binding.wbvWeb.loadUrl("http://br.cellep.com/estacaohack/")
        binding.wbvWeb.webViewClient = WebViewClient()
    }
}