package ru.uomkri.marlerinotest.screens.web

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.fragment.app.Fragment
import ru.uomkri.marlerinotest.databinding.FragmentWebBinding

class WebFragment : Fragment() {

    private lateinit var binding: FragmentWebBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentWebBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val currentLanguage = resources.configuration.locale.language
        Log.e("lg", currentLanguage)

        val link = "https://google.com/?hl="

        binding.webView.apply {
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            webViewClient = CustomWebViewClient()
            loadUrl("$link$currentLanguage")
        }

        return binding.root
    }

    class CustomWebViewClient : WebViewClient() {
        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            view!!.loadUrl(request!!.url.toString())
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view!!.loadUrl(url!!)
            return true
        }
    }
}

