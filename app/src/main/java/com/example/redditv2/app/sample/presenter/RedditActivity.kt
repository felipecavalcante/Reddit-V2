package com.example.redditv2.app.sample.presenter

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.redditv2.R
import com.example.redditv2.app.base.adapters.InfiniteScrollListener
import com.example.redditv2.app.sample.domain.ChildrenData
import com.example.redditv2.app.sample.presenter.ui.RecyclerAdapter
import com.example.redditv2.databinding.ActivityRedditBinding
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_reddit.*
import javax.inject.Inject

class RedditActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: RedditActivityViewModel
    private lateinit var binding: ActivityRedditBinding
    val list = mutableListOf<ChildrenData>()
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
        binding.swipeLayout.setOnRefreshListener {
            viewModel.loading()
        }

    }

    private fun setupObserver() {
        viewModel.loading()
        viewModel.showLoading.observe(this, Observer {
            binding.swipeLayout.isRefreshing = it
        })
        viewModel.conection.observe(this, Observer {
            if (it == true) {
                val recycler = viewModel.list
                configureList(recycler)
            } else {
                AlertDialog.Builder(this)
                    .setMessage("Parece que estámos com um problema")
                    .setPositiveButton("Tentar novamente") { _, _ -> retry() }.show()
            }
        })
    }

    private fun retry() {
        viewModel.loading()
    }

    private fun configureList(news: List<ChildrenData>) {
        val recyclerView = reclyclerActivity
        list.map { news }
        recyclerView.adapter = RecyclerAdapter(this, list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        binding.reclyclerActivity.apply {
            adapter = RecyclerAdapter(context, viewModel.list)
            addOnScrollListener(InfiniteScrollListener(layoutManager) {
                viewModel.loading()
            })
        }
    }
}
