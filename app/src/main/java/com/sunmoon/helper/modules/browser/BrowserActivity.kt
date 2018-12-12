package com.sunmoon.helper.modules.browser

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import com.sunmoon.helper.R
import com.sunmoon.helper.base.BaseActivity
import com.sunmoon.helper.databinding.ActivityBrowserBinding
import com.sunmoon.helper.databinding.ActivitySearchBinding
import com.sunmoon.helper.modules.search.SearchViewModel

/**
 * Created by SunMoon on 2017/5/4.
 */

class BrowserActivity : BaseActivity() {
    private var b: ActivityBrowserBinding? = null
    private var vm: BrowserViewModel? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm = BrowserViewModel(intent.getStringExtra("url"))
        b = DataBindingUtil.setContentView(this, R.layout.activity_browser)
        setSupportActionBar(b!!.toolBar)
        b!!.toolBar.setNavigationOnClickListener { finish() }
        initWebSetting()
        b!!.wb.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                b!!.toolBar.title = title
            }
        }
        b!!.wb.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                return super.shouldOverrideUrlLoading(view, request)

            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }
        }
        b!!.vm = vm
    }

    private fun initWebSetting() {
        val settings = b!!.wb.settings
        settings.javaScriptEnabled = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.useWideViewPort = true
        settings.setSupportZoom(true)
        settings.builtInZoomControls = true
        settings.displayZoomControls = true
        settings.allowFileAccess = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (b!!.wb.canGoBack()) {
            b!!.wb.goBack()
        }
    }
}
