package com.zwl.playandroid.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zwl.playandroid.databinding.ListItemArticleBinding
import com.zwl.playandroid.db.entity.article.Article
import com.zwl.playandroid.ui.main.HomeFragmentDirections

/**
 * Create: 2020-01-02 16:05
 * version:
 * desc:
 *
 * @author Zouweilin
 */

class HomeArticleListAdapter :
    ListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(
            ListItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.apply {
            holder.itemView.tag = this
            (holder as HomeViewHolder).bind(this)
        }
    }

    class HomeViewHolder(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.apply {
                article = item
                executePendingBindings()
            }
        }

        init {
            binding.root.setOnClickListener {
                val article = it.tag as Article
                val directions =
                    HomeFragmentDirections.actionHomeViewPagerFragmentToBrowserFragment(article)
                it.findNavController().navigate(directions)
            }
        }
    }


}

/**
 * 数据去重
 */
private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
                && oldItem.zan == newItem.zan
    }

}