package com.zwl.playandroid.ui.browser

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zwl.playandroid.R
import com.zwl.playandroid.base.BaseFragment
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.BrowserFragmentBinding
import com.zwl.playandroid.ui.system.SystemViewModel
import kotlinx.android.synthetic.main.browser_fragment.*

/**
 * Create: 2020-01-02 10:24
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class BrowserFragment : BaseFragment() {

    private val arg: BrowserFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BrowserFragmentBinding.inflate(inflater, container, false)
        binding.article = arg.article
        binding.toolbar.init()
        binding.webView.init()
        refreshLayout?.init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    private fun start() {
        refreshLayout?.isRefreshing = true
        webView?.loadUrl(arg.article.link)
    }

    private fun Toolbar.init() {
        setNavigationOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                this.findNavController().popBackStack(R.id.browserFragment, true)
            }
        }
        setBackgroundColor(Color.WHITE)
    }

    private fun WebView.init() {
        webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (newProgress == 100) {
                    refreshLayout?.isRefreshing = false
                }
            }
        }
        settings.run {
            javaScriptEnabled = true
            loadWithOverviewMode = true
            useWideViewPort = true
        }
    }

    private fun SwipeRefreshLayout.init() {
        setOnRefreshListener {
            webView?.stopLoading()
            start()
        }
    }
}