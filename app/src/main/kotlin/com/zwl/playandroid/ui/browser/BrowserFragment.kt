package com.zwl.playandroid.ui.browser

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ShareCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zwl.playandroid.Injections
import com.zwl.playandroid.R
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.BrowserFragmentBinding
import com.zwl.playandroid.db.entity.article.Article
import kotlinx.android.synthetic.main.browser_fragment.*

/**
 * Create: 2020-01-02 10:24
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class BrowserFragment : BaseViewModelFragment<BrowserViewModel>() {

    private val arg: BrowserFragmentArgs by navArgs()
    private var browserX = 0
    private var browserY = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BrowserFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.toolbar.init()
        binding.webView.init()
        refreshLayout?.init()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.saveBrowser(browserX, browserY)
    }


    override fun initViewModel(dbName: String): BrowserViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            Injections.provideBrowserViewModelFactory(requireActivity(), dbName)
        )[BrowserViewModel::class.java]
    }

    override fun modelCreated(vm: BrowserViewModel) {
        vm.updateArticle(arg.article)
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
        setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.menu_browser -> {
                    createBrowserIntent()
                    true
                }
                R.id.menu_share -> {
                    createShareIntent()
                    true
                }
                else -> {
                    false
                }
            }
        }
        setBackgroundColor(Color.WHITE)

        viewModel.article.observe(viewLifecycleOwner,
            Observer<Article> { article ->
                Log.e("zzzz", "BrowserFragment - onChanged -> title:${article.title}")
                title = article.title
            })

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
        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            browserX = scrollX
            browserY = scrollY
        }
        viewModel.browserRecord.value?.get(viewModel.article.value?.title)?.also {
            val x = it.first
            val y = it.second
            this.scrollTo(x, y)
        }
    }

    private fun SwipeRefreshLayout.init() {
        setOnRefreshListener {
            webView?.stopLoading()
            start()
        }
    }

    private fun createBrowserIntent() {
        val link = viewModel.article.value?.link
        link?.also {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                val uri = Uri.parse(link)
                data = uri
            })
        }
    }

    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = viewModel.article.value.let { article ->
            if (article == null) {
                ""
            } else {
                article.title + "\n" + article.link
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(activity)
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }
}