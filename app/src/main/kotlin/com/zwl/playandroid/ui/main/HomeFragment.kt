package com.zwl.playandroid.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.zwl.playandroid.R
import com.zwl.playandroid.base.BaseFragment
import com.zwl.playandroid.databinding.HomeFragmentBinding
import com.zwl.playandroid.ui.DatabaseNameViewModel
import com.zwl.playandroid.ui.discover.DiscoverFragment
import com.zwl.playandroid.ui.homearticlelist.HomeArticleFragment
import com.zwl.playandroid.ui.square.SquareFragment

/**
 * Create: 2019-12-31 11:36
 * version: 1
 * desc:
 *
 * @author Zouweilin
 */
const val HOME_ARTICLE_INDEX = 0
const val SQUARE_INDEX = 1
const val DISCOVER_INDEX = 2

class HomeFragment : BaseFragment() {

    private lateinit var fragmentCreator: Map<Int, () -> BaseFragment>
    private var containerId: Int = -1


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("zzzz", "HomeFragment - onAttach -> ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("zzzz", "HomeFragment - onDestroy -> ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("zzzz", "HomeFragment - onDetach -> ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("zzzz", "HomeFragment - onCreate -> ")
        prepareFragments(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = HomeFragmentBinding.inflate(inflater, container, false)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.menu_article -> show(HOME_ARTICLE_INDEX).let { true }
                R.id.menu_square -> show(SQUARE_INDEX).let { true }
                R.id.menu_discover -> show(DISCOVER_INDEX).let { true }
                else -> false
            }
        }
        containerId = binding.homeFragmentsContainer?.id ?: throw NullPointerException("")

        Log.e("zzzz", "HomeFragment - onCreateView -> ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("zzzz", "HomeFragment - onViewCreated -> ")
        show(HOME_ARTICLE_INDEX)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("zzzz", "HomeFragment - onDestroyView -> ")
        remove()

    }

    private fun prepareFragments(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            return
        }
        Log.e("zzzz", "HomeFragment - prepareFragments -> ")
        fragmentCreator = createFragments()
    }

    private fun createFragments(): Map<Int, () -> BaseFragment> {
        val home = HomeArticleFragment()
        val square = SquareFragment()
        val discover = DiscoverFragment()
        return mutableMapOf(
            HOME_ARTICLE_INDEX to { home },
            SQUARE_INDEX to { square },
            DISCOVER_INDEX to { discover }
        )
    }

    private fun show(index: Int) {
        val manager = fragmentManager ?: return
        val fragment = fragmentCreator[index]?.invoke() ?: throw IndexOutOfBoundsException()
        val tag = fragment._tag
        Log.e("zzzz", "HomeFragment - show -> tag:$tag")
        if (fragment.isVisible) {
            return
        }
        val transaction = manager.beginTransaction()
        val tagFragment = manager.findFragmentByTag(tag)
        Log.e(
            "zzzz",
            "HomeFragment - show -> storeFragment:${fragment.hashCode()} tagFragment:${tagFragment.hashCode()}"
        )
        if (tagFragment != null) {
            transaction.show(tagFragment).hideExcept(tagFragment as BaseFragment).commit()
        } else {
            transaction.add(containerId, fragment, tag).show(fragment).hideExcept(fragment).commit()
        }
    }

    private fun remove() {
        val manager = fragmentManager ?: return
        val transaction = manager.beginTransaction()
        fragmentCreator.values.forEach { transaction.remove(it.invoke()) }
        transaction.commitAllowingStateLoss()
    }

    private fun FragmentTransaction.hideExcept(f: BaseFragment): FragmentTransaction {
        val filter = fragmentCreator.values.filter { it.invoke()._tag != f._tag }
        filter.forEach { this.hide(it.invoke()) }
        return this
    }

}
