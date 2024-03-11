package com.example.webview

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.webview.databinding.ActivityMainBinding

private const val SEARCH_TAG = "https://google.com/search?q="

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
        val colorMap = mapOf(
            R.id.color1 to R.color.green,
            R.id.color2 to R.color.blue,
            R.id.color3 to R.color.violet,
            R.id.color1_land to R.color.brown,
            R.id.color2_land to R.color.yellow,
            R.id.color3_land to R.color.orange
        )
        return colorMap[item.itemId]?.let { colorRes->
            binding.btnSearch.setBackgroundColor(getColor(colorRes))
            true
        } ?: super.onOptionsItemSelected(item)
    }
}