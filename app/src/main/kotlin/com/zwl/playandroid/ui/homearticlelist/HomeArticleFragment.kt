package com.zwl.playandroid.ui.homearticlelist

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.zwl.playandroid.Injections
import com.zwl.playandroid.R
import com.zwl.playandroid.adapters.HomeArticleListAdapter
import com.zwl.playandroid.app.DEFAULT_DB_NAME
import com.zwl.playandroid.base.BaseViewModelFragment
import com.zwl.playandroid.databinding.HomeArticleFragmentBinding
import com.zwl.playandroid.ui.PlayAndroidViewModel
import kotlinx.android.synthetic.main.home_article_fragment.*


class HomeArticleFragment : BaseViewModelFragment<PlayAndroidViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("zzzz", "HomeArticleFragment - onCreate -> savedInstanceState:$savedInstanceState")
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<HomeArticleFragmentBinding>(inflater, R.layout.home_article_fragment, container, false).apply {
            homeRecyclerView?.setUp(HomeArticleListAdapter())
        }
        (activity as AppCompatActivity).setSupportActionBar(binding.toolBar)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = viewModel.articleList.value
        Log.e("zzzz", "HomeArticleFragment - onViewCreated -> modelValue:$value")
        if (value.isNullOrEmpty()) {
            viewModel.requestMore()
        }
        fab?.restoreHierarchyState(sparseArray)
    }

    companion object {
        val sparseArray = SparseArray<Parcelable>()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.e("zzzz", "HomeArticleFragment - onSaveInstanceState -> ")
        fab?.saveHierarchyState(sparseArray)
    }

    override fun onStop() {
        super.onStop()

    }

    override fun initViewModel(dbName: String): PlayAndroidViewModel {
        return createVM(dbName)
    }


    private fun createVM(dbName: String = DEFAULT_DB_NAME): PlayAndroidViewModel {
        val activity = requireActivity()
        return ViewModelProviders.of(
                activity,
                Injections.providerAppViewModelFactory(activity, dbName)
        )[PlayAndroidViewModel::class.java]
    }

    private fun RecyclerView.setUp(adapter: HomeArticleListAdapter) {
        val layoutManager = layoutManager as androidx.recyclerview.widget.LinearLayoutManager
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalItemCount = layoutManager.itemCount
                val visibleItemCount = layoutManager.childCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                Log.e("zzzz", "visibleItemCount:$visibleItemCount - lastVisibleItemPosition:$lastVisibleItem -> totalItemCount:$totalItemCount")
                Log.d("zzzz", "dx:$dx - dy:$dy -> ")
                viewModel.listScrolled(visibleItemCount, lastVisibleItem, totalItemCount)
            }
        })
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        this.adapter = adapter
        subscribeUi(adapter)
    }

    private fun subscribeUi(adapter: HomeArticleListAdapter) {
        viewModel.articleList.observe(viewLifecycleOwner, Observer { articleList ->
            adapter.submitList(articleList)
        })
    }
}


