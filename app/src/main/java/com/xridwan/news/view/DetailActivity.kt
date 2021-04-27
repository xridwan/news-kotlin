package com.xridwan.news.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import com.xridwan.news.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_URL = "extra_url"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra(EXTRA_URL)

        binding.webNews.webViewClient = WebViewClient()
        binding.webNews.settings.javaScriptEnabled = true
        binding.webNews.isVerticalScrollBarEnabled = true
        binding.webNews.loadUrl(url.toString())
        binding.webNews.webChromeClient = WebChromeClient()
    }
}