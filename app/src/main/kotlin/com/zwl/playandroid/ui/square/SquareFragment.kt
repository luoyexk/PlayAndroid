package com.zwl.playandroid.ui.square

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.zwl.playandroid.Injections
import com.zwl.playandroid.R
import com.zwl.playandroid.adapters.HomeArticleListAdapter
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.SquareFragmentBinding
import kotlinx.android.synthetic.main.square_fragment.*

/**
 * Create: 2020-01-02 10:18
 * version:
 * desc:
 *
 * @author Zouweilin
 */
class SquareFragment : BaseViewModelFragment<SquareViewModel>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<SquareFragmentBinding>(inflater, R.layout.square_fragment, container, false)
            .apply {
                viewModel = this@SquareFragment.viewModel
                lifecycleOwner = this@SquareFragment.viewLifecycleOwner
                toolBar.init()
                squareRecyclerView.init()
                squareRefresh.init()
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        start()
    }

    private fun start() {
        if (viewModel.articles.value?.size == 0) {
            square_refresh.isRefreshing = true
            viewModel.requestMore()
        }
    }

    private fun subscribeUi(adapter: HomeArticleListAdapter) {
        viewModel.articles.observe(viewLifecycleOwner, Observer {
            square_refresh.isRefreshing = false
            Log.e("zzzz","SquareFragment - subscribeUi -> newSize:${it.size}" )
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initViewModel(dbName: String): SquareViewModel {
        return createVM(dbName)
    }

    private fun createVM(dbName: String): SquareViewModel {
        return ViewModelProviders.of(
            requireActivity(),
            Injections.providerAppViewModelFactory(requireActivity(), dbName)
        )[SquareViewModel::class.java]
    }

    private fun Toolbar.init() {
        setOnMenuItemClickListener { item: MenuItem? ->
            when (item?.itemId) {
                R.id.menu_share_article -> {
                    Toast.makeText(requireContext(), R.string.function_processing, Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }

    private fun RecyclerView.init() {
        val adapter = HomeArticleListAdapter()
        this.adapter = adapter
        subscribeUi(adapter)
        val layoutManager = layoutManager as androidx.recyclerview.widget.LinearLayoutManager

        setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val totalItemCount = layoutManager.itemCount
            val visibleItemCount = layoutManager.childCount
            val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
            viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
        }

    }

    private fun SwipeRefreshLayout.init() {
        setOnRefreshListener {
            isRefreshing = true
            viewModel.refresh()
        }
    }

}
