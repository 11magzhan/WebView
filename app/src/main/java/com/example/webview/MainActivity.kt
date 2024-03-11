package com.example.webview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityMainBinding

private const val SEARCH_TAG = "https://google.com/search?q=%d"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setSupportActionBar(binding.toolbar)
        setContentView(binding.root)

        binding.btnSearch.setOnClickListener {
            val url = binding.etLink.text.toString()
            webViewSetup(url)
        }
    }

    private fun webViewSetup(url: String) {
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl("$SEARCH_TAG$url")

        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                val newTitle = view.title
                supportActionBar?.title = newTitle
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.color1 -> {
                binding.btnSearch.setBackgroundColor(getColor(R.color.green))
                true
            }
            R.id.color2 -> {
                binding.btnSearch.setBackgroundColor(getColor(R.color.blue))
                true
            }
            R.id.color3 -> {
                binding.btnSearch.setBackgroundColor(getColor(R.color.violet))
                true
            }
            R.id.color1_land -> {
                binding.btnSearch.setBackgroundColor(getColor(R.color.brown))
                true
            }
            R.id.color2_land -> {
                binding.btnSearch.setBackgroundColor(getColor(R.color.yellow))
                true
            }
            R.id.color3_land -> {
                binding.btnSearch.setBackgroundColor(getColor(R.color.orange))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}