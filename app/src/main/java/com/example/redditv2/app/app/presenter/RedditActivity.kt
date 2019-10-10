package com.example.redditv2.app.app.presenter

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditv2.R
import com.example.redditv2.app.app.base.adapters.InfiniteScrollListener
import com.example.redditv2.app.app.domain.ChildrenData
import com.example.redditv2.app.app.presenter.ui.RecyclerAdapter
import com.example.redditv2.databinding.ActivityRedditBinding
import dagger.android.AndroidInjection
import javax.inject.Inject

class RedditActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RedditActivityViewModel
    private lateinit var binding: ActivityRedditBinding
    private val list = ArrayList<ChildrenData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(RedditActivityViewModel::class.java)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reddit)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        setupObserver()
        setupBindings()

    }

    private fun setupBindings() {
        val linearLayoutManager = LinearLayoutManager(this)

        binding.swipeLayout.setOnRefreshListener {
            retry()
        }
        binding.reclyclerActivity.apply {
            layoutManager = linearLayoutManager
            addOnScrollListener(InfiniteScrollListener(
            linearLayoutManager
        ) {
            viewModel.loading()
        })
        }
    }

    private fun setupObserver() {
        viewModel.loading()
        viewModel.showLoading.observe(this, Observer {
            binding.swipeLayout.isRefreshing = it
        })
        viewModel.conection.observe(this, Observer {
            if (it == true) {
                list.addAll(viewModel.list)
                configureList(list)
            } else {
                AlertDialog.Builder(this)
                    .setMessage("Parece que estámos com um problema")
                    .setPositiveButton("Tentar novamente") { _, _ -> retry() }.show()
            }
        })
    }

    private fun retry() {
        viewModel.afterCall.value = ""
        viewModel.loading()
    }

    private fun configureList(news: ArrayList<ChildrenData>) {
        binding.reclyclerActivity.adapter = RecyclerAdapter(this, news)
    }
}
